package sk.artifacts.task;

import sk.api.Task;
import sk.api.annotation.TaskConf;

@TaskConf(label = "Tarefa 1", order = 1)
public class Task1 implements Task {

	@Override
	public void run() {
		System.out.println("Tarefa 1");
	}

}
