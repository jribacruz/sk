package sk.artifacts.task;

import sk.api.Task;
import sk.api.annotation.TaskConf;

@TaskConf(label = "Tarefa 2", order = 2)
public class Task2 implements Task {

	@Override
	public void run() {
		System.out.println("Tarefa 2");
	}

}
