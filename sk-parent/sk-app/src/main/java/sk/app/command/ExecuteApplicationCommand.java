package sk.app.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import sk.api.annotation.CommandConf;
import sk.api.command.Command;
import sk.api.command.CommandContext;

@CommandConf(uuid = "exec", description = "Executa uma aplicação SK.")
public class ExecuteApplicationCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(CommandContext cmdContext) throws IOException {
		System.out.println("Exec..");
	}

	@Override
	public SortedSet<String> getCandidates() {
		return new TreeSet<>(Arrays.asList("a", "b", "c"));
	}

}
