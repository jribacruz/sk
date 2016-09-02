package sk.impl.reader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;

import jline.console.ConsoleReader;
import sk.api.Context;
import sk.api.Task;
import sk.api.exception.EmptyParamOptionException;
import sk.api.reader.Selectable;
import sk.api.reader.Selector;
import sk.api.util.Colorize;
import sk.api.util.Log;

public class SelectorImpl implements Selector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Context context;

	@Inject
	private Log log;

	private String selectOneErrorMessage = "Opção inválida: %s. Escolha apenas um valor entre 1 e %d.";

	private String selectManyErrorMessage = "Opção inválida: %s. Escolha um valor entre 1 e %d. Use vírgula para selecionar mais de uma opção. Digite * para selecionar todos.";

	private String confirmErrorMessage = "Opção inválida: %s. Escolha y para sim ou n para não.";

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectOne(java.lang.String, java.util.List)
	 */
	@Override
	public <X, T extends Selectable<X>> T selectOne(String prompt, List<T> selectableOptions) throws IOException {
		validateParamOptions(selectableOptions);
		Map<Integer, T> options = printSelectableOptions(selectableOptions);
		return readSelectOne(options, prompt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectOne(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public <X, T extends Selectable<X>> void selectOne(String prompt, String contextKey, List<T> selectableOptions) throws IOException {
		if (StringUtils.isNotBlank(contextKey)) {
			T t = selectOne(prompt, selectableOptions);
			context.put(contextKey, t);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectOne(java.lang.String, java.lang.Iterable)
	 */
	@Override
	public <X, T extends Selectable<X>> T selectOne(String prompt, Iterable<T> selectableOptions) throws IOException {
		return selectOne(prompt, Lists.newArrayList(selectableOptions));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectOne(java.lang.String, java.lang.String, java.lang.Iterable)
	 */
	@Override
	public <X, T extends Selectable<X>> void selectOne(String prompt, String contextKey, Iterable<T> selectableOptions) throws IOException {
		if (StringUtils.isNotBlank(contextKey)) {
			T t = selectOne(prompt, selectableOptions);
			context.put(contextKey, t);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectMany(java.lang.String, java.util.List)
	 */
	@Override
	public <X, T extends Selectable<X>> List<T> selectMany(String prompt, List<T> selectableOptions) throws IOException {
		validateParamOptions(selectableOptions);
		Map<Integer, T> options = printSelectableOptions(selectableOptions);
		return readSelectMany(options, prompt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectMany(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public <X, T extends Selectable<X>> void selectMany(String prompt, String contextKey, List<T> selectableOptions) throws IOException {
		if (StringUtils.isNotBlank(contextKey)) {
			List<T> ts = selectMany(prompt, selectableOptions);
			context.put(contextKey, ts);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectMany(java.lang.String, java.lang.Iterable)
	 */
	@Override
	public <X, T extends Selectable<X>> List<T> selectMany(String prompt, Iterable<T> selectableOptions) throws IOException {
		return selectMany(prompt, Lists.newArrayList(selectableOptions));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectMany(java.lang.String, java.lang.String, java.lang.Iterable)
	 */
	@Override
	public <X, T extends Selectable<X>> void selectMany(String prompt, String contextKey, Iterable<T> selectableOptions)
			throws IOException {
		if (StringUtils.isNotBlank(contextKey)) {
			List<T> ts = selectMany(prompt, selectableOptions);
			context.put(contextKey, ts);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectAndExecuteOne(java.lang.String, java.util.List)
	 */
	@Override
	public <T extends Task> void selectAndExecuteOne(String prompt, List<T> taskOptions) throws IOException {
		validateParamOptions(taskOptions);
		Map<Integer, T> options = printSelectableOptions(taskOptions);
		readSelectOne(options, prompt).run();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectAndExecuteOne(java.lang.String, java.lang.Iterable)
	 */
	@Override
	public <T extends Task> void selectAndExecuteOne(String prompt, Iterable<T> taskOptions) throws IOException {
		selectAndExecuteOne(prompt, Lists.newArrayList(taskOptions));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectAndExecuteMany(java.lang.String, java.util.List)
	 */
	@Override
	public <T extends Task> void selectAndExecuteMany(String prompt, List<T> taskOptions) throws IOException {
		validateParamOptions(taskOptions);
		Map<Integer, T> options = printSelectableOptions(taskOptions);
		readSelectMany(options, prompt).parallelStream().forEach(task -> task.run());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#selectAndExecuteMany(java.lang.String, java.lang.Iterable)
	 */
	@Override
	public <T extends Task> void selectAndExecuteMany(String prompt, Iterable<T> taskOptions) throws IOException {
		selectAndExecuteMany(prompt, Lists.newArrayList(taskOptions));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.input.Selector#confirm(java.lang.String, sk4j.core.Task)
	 */
	@Override
	public <T extends Task> void confirm(String message, T taskOption) throws IOException {
		String value = readConsole(message.concat(" (y/n)"));
		if (Arrays.asList("y", "n").contains(value)) {
			if (value.equals("y")) {
				taskOption.run();
				return;
			}
			return;
		}
		log.warn(String.format(confirmErrorMessage, value));
		confirm(message, taskOption);
	}

	/**
	 * 
	 * @param selectableOptions
	 * @return
	 */
	private <X, T extends Selectable<X>> Map<Integer, T> printSelectableOptions(List<T> selectableOptions) {
		AtomicInteger counter = new AtomicInteger(0);
		Map<Integer, T> optionMap = new HashMap<>();
		System.out.println("");
		//@formatter:off
		selectableOptions
			.stream()
			.sorted()
			.forEach(s -> {
				optionMap.put(counter.incrementAndGet(), s);
				System.out.println(String.format("%s : %s", Colorize.magenta(counter.get()), context.replace(s.getConsoleLabel())));
			});
		//@formatter:on
		return optionMap;
	}

	/**
	 * 
	 * @param options
	 * @param message
	 * @return
	 * @throws IOException
	 */
	private <X, T extends Selectable<X>> T readSelectOne(Map<Integer, T> options, String message) throws IOException {
		String value = readConsole(message);
		if (StringUtils.isNumeric(value)) {
			Integer option = Integer.valueOf(value);
			if (Range.closed(1, options.size()).contains(option)) {
				return options.get(option);
			}
		}
		log.warn(String.format(selectOneErrorMessage, value, options.size()));
		return readSelectOne(options, message);
	}

	/**
	 * Seleciona varios selectable.
	 * 
	 * @param options
	 * @param message
	 * @return
	 * @throws IOException
	 */
	private <X, T extends Selectable<X>> List<T> readSelectMany(Map<Integer, T> options, String message) throws IOException {
		String value = readConsole(message);
		Range<Integer> optionRange = Range.closed(1, options.size());
		//@formatter:off
		List<String> values = value.equals("*") ? options.keySet().stream().map(String::valueOf).collect(Collectors.toList()) :
      							   Arrays.asList(value.split(",")).stream().distinct().collect(Collectors.toList());
		//@formatter:on
		if (values.stream().allMatch(StringUtils::isNumeric)) {
			List<Integer> optionValues = values.stream().map(Integer::valueOf).collect(Collectors.toList());
			if (optionValues.stream().allMatch(optionRange::contains)) {
				//@formatter:off
				return options.keySet()
							.stream()
							.filter(optionValues::contains)
							.map(options::get)
							.collect(Collectors.toList());
				//@formatter:on
			}
		}
		log.warn(String.format(selectManyErrorMessage, value, options.size()));
		return readSelectMany(options, message);
	}

	/**
	 * Lê um string do console.
	 * 
	 * @param prompt
	 *            Mensagem do prompt.
	 * @return String do console.
	 * @throws IOException
	 *             Erro na leitura do console.
	 */
	private String readConsole(String prompt) throws IOException {
		ConsoleReader consoleReader = new ConsoleReader();
		consoleReader.setHandleUserInterrupt(true);
		String value = consoleReader.readLine(String.format("> %s: ", prompt));
		consoleReader.close();
		return StringUtils.trim(value);
	}

	private <X, T extends Selectable<X>> void validateParamOptions(List<T> options) {
		if (options == null || options.isEmpty()) {
			throw new EmptyParamOptionException();
		}
	}

}
