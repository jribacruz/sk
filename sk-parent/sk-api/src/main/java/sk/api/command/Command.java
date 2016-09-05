package sk.api.command;

import java.io.IOException;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import sk.api.annotation.CommandConf;

public interface Command extends Serializable {

	void execute(CommandContext cmdContext) throws IOException;

	default SortedSet<String> getCandidates() {
		return new TreeSet<>();
	}

	default String getUUID() {
		return this.getClass().getAnnotation(CommandConf.class).uuid();
	}

	default String getDescription() {
		return this.getClass().getAnnotation(CommandConf.class).description();
	}
}
