package sk.app;

import java.io.File;
import java.io.IOException;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import jline.console.UserInterruptException;
import sk.api.Context;
import sk.api.event.AfterInit;
import sk.api.util.Colorize;
import sk.app.exception.ArgumentNotFoundException;
import sk.app.exception.ProjectJavaNotFoundException;
import sk.impl.Beans;

public class Bootstrap {

	@Inject
	private Event<AfterInit> afterInitEvent;

	@Inject
	private Context context;

	public static void main(String[] args) {
		WeldContainer container = null;
		try {
			Bootstrap.validateApp(args);
			Weld weld = new Weld();
			container = weld.initialize();
			Beans.setBeanManager(container.getBeanManager());
			Bootstrap mainApp = container.instance().select(Bootstrap.class).get();
			mainApp.welcomeSK();
			mainApp.init(args);
		} catch (ArgumentNotFoundException e) {
			System.out.println(Colorize.red(e.getMessage()));
		} catch (UserInterruptException e) {
			System.out.println(Colorize.blue("\n Bye SK. \n"));
		} catch (ProjectJavaNotFoundException e) {
			System.out.println(Colorize.red(e.getMessage()));
		} catch (IOException e) {
			System.out.println(Colorize.red(e.getMessage()));
		} finally {
			if (container != null) {
				container.close();
			}
		}
	}

	private void init(String args[]) throws IOException {

		context.put("PROJECT_HOME", FilenameUtils.normalize(args[0]));

		afterInitEvent.fire(new AfterInit() {
			private static final long serialVersionUID = 1L;
		});
	}

	private static void validateApp(String[] args) throws ArgumentNotFoundException, ProjectJavaNotFoundException {
		if (args.length == 0) {
			throw new ArgumentNotFoundException("\nÉ necessário indicar o caminho de um projeto maven como parametro.\n");
		}
		File projectPath = new File(args[0]);
		File projectPom = new File(projectPath.getAbsolutePath().concat("/pom.xml"));
		if (!projectPom.exists()) {
			throw new ProjectJavaNotFoundException("\nNenhum projeto java encontrado no diretório especificado.\n");
		}
	}

	private void welcomeSK() {
		//@formatter:off
		System.out.println(Colorize.blue("\t__          __  _                             _____ _  __  _ "));
		System.out.println(Colorize.blue("\t\\ \\        / / | |                           / ____| |/ / | |"));
		System.out.println(Colorize.blue("\t \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___  | (___ | ' /  | |"));
		System.out.println(Colorize.blue("\t  \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\  \\___ \\|  <   | |  "));
		System.out.println(Colorize.blue("\t   \\  /\\  /  __/ | (_| (_) | | | | | |  __/  ____) | . \\  |_|"));
		System.out.println(Colorize.blue("\t    \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___| |_____/|_|\\_\\ (_)"));
		System.out.println("");
		//@formatter:on  
	}
}
