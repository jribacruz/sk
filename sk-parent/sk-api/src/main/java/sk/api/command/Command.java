package sk.api.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sk.api.annotation.CommandConf;

public interface Command extends Serializable {

	void execute(CommandContext cmdContext);

	default List<CharSequence> getCandidates() {
		return new ArrayList<>();
	}

	default String getUUID() {
		return this.getClass().getAnnotation(CommandConf.class).uuid();
	}

	default String getDescription() {
		return this.getClass().getAnnotation(CommandConf.class).description();
	}
}
