package sk.impl.producer;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import sk.api.model.EJavaProject;
import sk.api.util.Colorize;

public class PromptIDProducer {

	@Inject
	private EJavaProject eJavaProject;

	@Produces
	@Named("PROMP_ID")
	public String create() {
		return String.format("%s@%s", Colorize.bold(Colorize.blue("sk")), Colorize.bold(Colorize.green(eJavaProject.getProjectName())));
	}
}
