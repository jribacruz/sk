package sk.app.producer;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import sk.api.annotation.CommandConf;
import sk.api.command.Command;

public class CommandProducer {

	@Inject
	private Instance<Command> commands;

	private Map<String, Command> commandMap = new HashMap<>();

	@Produces
	public Map<String, Command> create() {
		commands.forEach(command -> {
			if (command.getClass().isAnnotationPresent(CommandConf.class)) {
				commandMap.put(command.getUUID(), command);
			}
		});
		return commandMap;
	}

}
