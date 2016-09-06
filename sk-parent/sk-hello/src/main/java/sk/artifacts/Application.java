package sk.artifacts;

import java.io.IOException;

import sk.api.SkApplication;
import sk.api.annotation.ApplicationConf;

/**
 * Hello world!
 *
 */
@ApplicationConf(uuid = "sk-hello", description = "Welcome SK.")
public class Application implements SkApplication {

	@Override
	public void run() throws IOException {
		System.out.println("It works!");
	}

}
