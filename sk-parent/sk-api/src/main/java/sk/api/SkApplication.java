package sk.api;

import sk.api.annotation.ApplicationConf;

/**
 * 
 * @author jcruz
 *
 */
public interface SkApplication {

	public void run();

	default String getDescription() {
		return this.getClass().getAnnotation(ApplicationConf.class).description();
	}

	default String getUUID() {
		return this.getClass().getAnnotation(ApplicationConf.class).uuid();
	}
}
