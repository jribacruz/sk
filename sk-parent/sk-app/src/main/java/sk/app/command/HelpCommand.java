package sk.app.command;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import sk.api.annotation.CommandConf;
import sk.api.command.Command;
import sk.api.command.CommandContext;

@CommandConf(uuid = "help", description = "Ajuda SK.")
public class HelpCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Map<String, Command> commands;

	@Override
	public void execute(CommandContext cmdContext) throws IOException {

	}

}
