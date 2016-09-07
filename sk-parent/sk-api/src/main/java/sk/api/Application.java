package sk.api;

import java.io.IOException;

import sk.api.annotation.ApplicationConf;

/**
 * 
 * @author jcruz
 *
 */
public interface Application {

	public void run() throws IOException;

	default String getDescription() {
		return this.getClass().getAnnotation(ApplicationConf.class).description();
	}

	default String getUUID() {
		return this.getClass().getAnnotation(ApplicationConf.class).uuid();
	}
}
