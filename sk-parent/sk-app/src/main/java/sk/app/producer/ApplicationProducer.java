package sk.app.producer;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import sk.api.Application;
import sk.api.annotation.ApplicationConf;

public class ApplicationProducer {

	@Inject
	private Instance<Application> applications;

	public Map<String, Application> applicationMap = new HashMap<>();

	@Produces
	@Default
	public Map<String, Application> create() {
		applications.forEach(app -> {
			if (app.getClass().isAnnotationPresent(ApplicationConf.class)) {
				applicationMap.put(app.getUUID(), app);
			}
		});
		return applicationMap;
	}
}
