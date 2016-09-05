package sk.app.command;

import java.io.IOException;

import sk.api.annotation.CommandConf;
import sk.api.command.Command;
import sk.api.command.CommandContext;

@CommandConf(uuid = "help", description = "Ajuda SK.")
public class HelpCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(CommandContext cmdContext) throws IOException {

	}

}
