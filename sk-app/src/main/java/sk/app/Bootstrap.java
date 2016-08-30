package sk.app;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import sk.api.util.Colorize;
import sk.impl.Beans;

public class Bootstrap {

	public static void main(String[] args) throws Exception {
		Bootstrap.validateApp(args);

		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		Beans.setBeanManager(container.getBeanManager());
		Bootstrap mainApp = container.instance().select(Bootstrap.class).get();
		mainApp.start(args);
		container.close();
	}

	private void start(String args[]) throws Exception {

	}

	private static void validateApp(String[] args) {
		if (args.length == 0) {
			System.out.println(Colorize.red("É necessário indicar o caminho de um projeto maven como parametro."));
			System.exit(0);
		}
	}
}
