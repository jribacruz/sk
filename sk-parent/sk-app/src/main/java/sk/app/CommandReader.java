package sk.app;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import jline.console.ConsoleReader;

public class CommandReader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Named("PROMP_ID")
	private String PROMPT_ID;

	@Inject
	private CommandCompleter commandCompleter;

	public Optional<String> read() throws IOException {
		ConsoleReader skReader = new ConsoleReader();
		skReader.setHandleUserInterrupt(true);
		skReader.addCompleter(commandCompleter);
		String command = StringUtils.trim(skReader.readLine(PROMPT_ID + "> "));
		skReader.close();
		return StringUtils.isNotBlank(command) ? Optional.of(command) : Optional.empty();
	}

}
