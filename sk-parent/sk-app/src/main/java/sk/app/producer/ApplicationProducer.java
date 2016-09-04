package sk.app.producer;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import sk.api.SkApplication;
import sk.api.annotation.ApplicationConf;

public class ApplicationProducer {

	@Inject
	private Instance<SkApplication> applications;

	public Map<String, SkApplication> applicationMap = new HashMap<>();

	@Produces
	@Default
	public Map<String, SkApplication> create() {
		applications.forEach(app -> {
			if (app.getClass().isAnnotationPresent(ApplicationConf.class)) {
				applicationMap.put(app.getUUID(), app);
			}
		});
		return applicationMap;
	}
}
