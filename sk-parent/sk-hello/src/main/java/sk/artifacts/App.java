package sk.artifacts;

import java.io.IOException;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import sk.api.Application;
import sk.api.Task;
import sk.api.annotation.ApplicationConf;
import sk.api.reader.Selector;

/**
 * Hello world!
 *
 */
@ApplicationConf(uuid = "sk-hello", description = "Welcome SK.")
public class App implements Application {

	@Inject
	private Selector selector;

	@Inject
	private Instance<Task> tasks;

	@Override
	public void run() throws IOException {
		System.out.println("It works!");
		selector.selectAndExecuteMany("Selecione a terefa", tasks);
	}

}
