package sk.app;

import static jline.internal.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import jline.console.completer.Completer;
import sk.api.command.Command;

public class CommandCompleter implements Completer {

	@Inject
	private Map<String, Command> commands;

	private SortedSet<String> commandCandidates;

	@Override
	public int complete(String buffer, int cursor, List<CharSequence> candidates) {
		checkNotNull(candidates);

		if (buffer == null) {
			candidates.addAll(getCommandCandidates());
		} else {
			for (String match : getCommandCandidates().tailSet(buffer)) {
				if (!match.startsWith(buffer)) {
					break;
				}
				candidates.add(match);
			}
			String[] commandTokens = StringUtils.split(buffer);
			if (commandTokens.length > 0) {
				Command command = commands.get(commandTokens[0]);
				if (command != null) {
					if (commandTokens.length == 1) {
						candidates.addAll(command.getCandidates());
					} else {
						for (String match : command.getCandidates().tailSet(commandTokens[1])) {
							if (!match.startsWith(commandTokens[1])) {
								break;
							}
							candidates.add(match);
						}
					}
					return command.getUUID().length() + 1;
				}
			}

		}

		return candidates.isEmpty() ? -1 : 0;
	}

	private SortedSet<String> getCommandCandidates() {
		if (this.commandCandidates == null) {
			this.commandCandidates = new TreeSet<>();
			this.commandCandidates.addAll(commands.keySet());
		}
		return this.commandCandidates;
	}

	@Override
	public String toString() {
		return "CommandCompleter [commands=" + commands + "]";
	}

}
