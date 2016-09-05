package sk.app.command;

import java.io.IOException;

import jline.console.UserInterruptException;
import sk.api.annotation.CommandConf;
import sk.api.command.Command;
import sk.api.command.CommandContext;

@CommandConf(uuid = "bye", description = "Sai do console SK.")
public class ByeCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(CommandContext cmdContext) throws IOException {
		throw new UserInterruptException("");
	}

}
