package sk.app;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import jline.console.ConsoleReader;
import jline.console.completer.StringsCompleter;
import sk.api.Context;
import sk.api.SkApplication;
import sk.api.event.AfterInit;
import sk.api.model.EJavaProject;
import sk.api.util.Colorize;

/**
 * Classe que chama os aplicativos SK.
 * 
 * @author jcruz
 *
 */
public class Main implements Serializable {

	@Inject
	private Context context;

	@Inject
	private EJavaProject eJavaProject;

	@Inject
	private Map<String, SkApplication> applicationMap;

	private StringsCompleter skCompleter;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void execute(@Observes AfterInit afterInit) throws IOException {
		context.put("PROMPT_ID",
				String.format("%s@%s", Colorize.bold(Colorize.blue("sk")), Colorize.bold(Colorize.green(eJavaProject.getProjectName()))));
		dispatcher();
	}

	private void dispatcher() throws IOException {
		String uuid = skPrompt();
		if (applicationMap.get(uuid) == null) {
			dispatcher();
		}
		applicationMap.get(uuid).run();
		dispatcher();
	}

	private String skPrompt() throws IOException {
		ConsoleReader skReader = new ConsoleReader();
		skReader.setHandleUserInterrupt(true);
		skReader.addCompleter(getSkCompleter());
		String uuid = skReader.readLine(context.get("PROMPT_ID") + "> ");
		skReader.close();
		return StringUtils.trim(uuid);
	}

	private StringsCompleter getSkCompleter() {
		if (skCompleter == null) {
			Collection<String> skCompleters = applicationMap.keySet();
			this.skCompleter = new StringsCompleter(skCompleters);
		}
		return this.skCompleter;
	}

}
