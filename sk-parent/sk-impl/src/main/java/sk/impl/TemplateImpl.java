package sk.impl;

import javax.inject.Inject;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import sk.api.Context;
import sk.api.Template;
import sk.api.enums.MavenFolder;
import sk.api.model.EJavaPackage;

public class TemplateImpl implements Template {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Context context;

	@Inject
	public TemplateImpl(Context context) {
		super();
		this.context = context;
	}

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
	 * @see sk.api.Template#mergeAndCreateFile(java.lang.String, sk.api.enums.MavenFolder)
	 */
	@Override
	public void mergeAndCreateFile(String templateName, MavenFolder mf) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.template.Template#mergeAndCreateFile(java.lang.String, java.lang.String)
	 */
	@Override
	public void mergeAndCreateFile(String templateName, MavenFolder mf, String path) {
		String merged = this.merge(templateName);
		// String finalPath = FilenameUtils.normalize(eJavaProject.getPathName().concat(mf.getPath()).concat(context.replace(path)));
		throw new UnsupportedOperationException();

	}

	@Override
	public void mergeAndCreateFile(String templateName, MavenFolder mf, EJavaPackage eJavaPackage, String path) {

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
