package sk.api.command;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public interface CommandContext extends Serializable {

	Map<String, Command> getCommands();

	Optional<String> getParamByIndex(int index);

	String getCommandUUID();

}
