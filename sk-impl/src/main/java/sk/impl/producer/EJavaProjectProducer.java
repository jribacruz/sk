package sk.impl.producer;

import java.io.File;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import sk.api.Context;
import sk.api.model.EJavaProject;
import sk.impl.model.EJavaProjectImpl;

public class EJavaProjectProducer {

	@Inject
	private Context context;

	@Produces
	@Singleton
	public EJavaProject javaProject() {
		File projectFile = new File((String) context.get("PROJECT_HOME"));
		return new EJavaProjectImpl(projectFile);
	}

}
