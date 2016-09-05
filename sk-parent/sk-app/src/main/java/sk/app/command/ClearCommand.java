package sk.app.command;

import sk.api.annotation.CommandConf;
import sk.api.command.Command;
import sk.api.command.CommandContext;

@CommandConf(uuid = "clear", description = "Limpa o console")
public class ClearCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(CommandContext cmdContext) {
		if (cmdContext.getParamByIndex(0).isPresent()) {
			System.out.println("Clear: " + cmdContext.getParamByIndex(0).get());
		}
	}

}
