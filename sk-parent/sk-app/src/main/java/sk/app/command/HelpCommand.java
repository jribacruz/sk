package sk.app.command;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

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
	public void execute(CommandContext commandContext) throws IOException {
		int rightPad = commandContext.getCommands().keySet().stream().mapToInt(String::length).reduce(Integer::max).getAsInt();
		System.out.println("");
		commandContext.getCommands().forEach((key, command) -> {
			System.out.println(String.format(" %s - %s", StringUtils.rightPad(command.getUUID(), rightPad), command.getDescription()));
		});
		System.out.println("");
	}

}
