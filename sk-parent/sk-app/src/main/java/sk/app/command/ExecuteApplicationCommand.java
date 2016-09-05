package sk.app.command;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;

import sk.api.SkApplication;
import sk.api.annotation.CommandConf;
import sk.api.command.Command;
import sk.api.command.CommandContext;
import sk.api.util.Colorize;

@CommandConf(uuid = "exec", description = "Executa uma aplicação SK.")
public class ExecuteApplicationCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Map<String, SkApplication> applications;

	@Override
	public void execute(CommandContext cmdContext) throws IOException {
		Optional<String> cmdApplicationParam = cmdContext.getParamByIndex(0);
		if (cmdApplicationParam.isPresent()) {
			SkApplication application = applications.get(cmdApplicationParam.get());
			if (application != null) {
				System.out.println(Colorize.yellow(String.format("\n> [ENTER] %s\n", application.getUUID())));
				application.run();
				System.out.println(Colorize.yellow(String.format("\n> [EXIT]  %s\n", application.getUUID())));
			}
		}
	}

	@Override
	public SortedSet<String> getCandidates() {
		return new TreeSet<>(applications.keySet());
	}

}
