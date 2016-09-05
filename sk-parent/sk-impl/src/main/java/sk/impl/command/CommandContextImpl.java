package sk.impl.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import sk.api.command.CommandContext;

public class CommandContextImpl implements CommandContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String commandInput;

	private List<String> params;

	private String commandUUID;

	public CommandContextImpl(String commandInput) {
		super();
		this.commandInput = commandInput;
		this.parseCommandInput();
	}

	private void parseCommandInput() {
		List<String> commandTokens = Arrays.asList(StringUtils.split(commandInput));
		this.commandUUID = commandTokens.get(0);
		this.params = commandTokens.size() > 1 ? commandTokens.subList(1, commandTokens.size()) : new ArrayList<>();
	}

	@Override
	public Optional<String> getParamByIndex(int index) {
		if (index < params.size()) {
			return Optional.of(params.get(index));
		}
		return Optional.empty();
	}

	@Override
	public String getCommandUUID() {
		return this.commandUUID;
	}

}
