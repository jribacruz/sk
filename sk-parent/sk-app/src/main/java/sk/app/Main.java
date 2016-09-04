package sk.app;

import java.io.Serializable;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import sk.api.SkApplication;
import sk.api.event.AfterInit;
import sk.api.model.EJavaProject;

/**
 * Classe que chama os aplicativos SK.
 * 
 * @author jcruz
 *
 */
public class Main implements Serializable {

	@Inject
	private Instance<SkApplication> applications;

	@Inject
	private EJavaProject eJavaProject;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void execute(@Observes AfterInit afterInit) {
		applications.forEach(app -> app.run());
	}

}
