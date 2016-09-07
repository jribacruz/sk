package sk.app;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import sk.api.command.Command;
import sk.api.command.CommandContext;
import sk.api.event.AfterInit;
import sk.impl.command.CommandContextImpl;

/**
 * Classe que chama os aplicativos SK.
 * 
 * @author jcruz
 *
 */
public class Main implements Serializable {

	@Inject
	private CommandReader commandReader;

	@Inject
	private Map<String, Command> commands;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void execute(@Observes AfterInit afterInit) throws IOException {
		commandDispatcher();
	}

	private void commandDispatcher() throws IOException {
		Optional<String> commandOpt = commandReader.read();
		if (commandOpt.isPresent()) {
			CommandContext commandContext = new CommandContextImpl(commandOpt.get(), commands);
			if (commands.containsKey(commandContext.getCommandUUID())) {
				Command command = commands.get(commandContext.getCommandUUID());
				command.execute(commandContext);
			}
		}
		commandDispatcher();
	}

}
