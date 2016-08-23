package sk.app;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import sk.impl.Beans;

public class Bootstrap {
	public static void main(String[] args) throws Exception {

		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		Beans.setBeanManager(container.getBeanManager());
		Bootstrap mainApp = container.instance().select(Bootstrap.class).get();
		mainApp.start(args);

		System.out.println(System.getProperty("user.dir"));
		System.out.println(args[0]);
	}

	private void start(String args[]) throws Exception {

	}
}
