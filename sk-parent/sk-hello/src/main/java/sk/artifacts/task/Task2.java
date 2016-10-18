package sk.artifacts.task;

import javax.inject.Inject;

import sk.api.Task;
import sk.api.annotation.TaskConf;
import sk.api.enums.MavenFolder;
import sk.api.model.EJavaProject;

@TaskConf(label = "Exibir todas as classes do projeto.", order = 2)
public class Task2 implements Task {

	@Inject
	private EJavaProject eJavaProject;

	@Override
	public void run() {
		//@formatter:off
		eJavaProject.getEJavaClasses(MavenFolder.SRC_MAIN_JAVA)
			.stream()
			.forEach(eJavaClass -> System.out.println(eJavaClass.getClassName()));
		//@formatter:on
	}

}
