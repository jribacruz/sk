package sk.api.command;

import java.io.Serializable;
import java.util.Optional;

public interface CommandContext extends Serializable {

	Optional<String> getParamByIndex(int index);

	String getCommandUUID();

}
