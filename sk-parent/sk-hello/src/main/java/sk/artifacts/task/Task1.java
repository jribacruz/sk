package sk.artifacts.task;

import javax.inject.Inject;

import sk.api.Task;
import sk.api.Template;
import sk.api.annotation.TaskConf;
import sk.api.enums.MavenFolder;
import sk.api.reader.Reader;
import sk.artifacts.name.UserName;

/**
 * 
 * Testa a geração de arquivos via template lendo dados do console.
 * 
 * @author jcruz
 *
 */
@TaskConf(label = "Gerar welcome.html no /target do projeto.", order = 1)
public class Task1 implements Task {

	@Inject
	private Template template;

	@Inject
	private Reader reader;

	@Override
	public void run() {
		reader.read("Digite o nome do usuário", new UserName());
		template.mergeAndCreateFile("welcome.jtwig", MavenFolder.TARGET, "welcome.html");
	}

}
