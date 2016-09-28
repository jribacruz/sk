package sk.impl;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import sk.api.Context;
import sk.api.Template;
import sk.api.enums.MavenFolder;
import sk.api.model.EJavaProject;

public class TemplateImpl implements Template {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Context context = Beans.getReference(Context.class);

	private EJavaProject eJavaProject = Beans.getReference(EJavaProject.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.template.Template#merge()
	 */
	@Override
	public String merge(String templateName) {
		return JtwigTemplate.classpathTemplate(String.format("/templates/%s.jtwig", templateName)).render(this.createJtwigModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.template.Template#mergeAndCreateFile(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void mergeAndCreateFile(String templateName, MavenFolder mf, String path) throws IOException {
		String merged = this.merge(templateName);
		String finalPath = FilenameUtils.normalize(eJavaProject.getPathName().concat(mf.getPath()).concat(context.replace(path)));
		throw new UnsupportedOperationException();
		
	}

	/*
	 * Cria um model do template e popula com as variáveis do context.
	 */
	private JtwigModel createJtwigModel() {
		JtwigModel jtwigModel = JtwigModel.newModel();
		context.getContext().forEach((k, v) -> jtwigModel.with(k, v));
		return jtwigModel;
	}

}
