package sk.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.resource.exceptions.ResourceNotFoundException;

import sk.api.Context;
import sk.api.Template;
import sk.api.enums.MavenFolder;
import sk.api.model.EJavaPackage;
import sk.api.util.Colorize;
import sk.api.util.FS;

public class TemplateImpl implements Template {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Context context;

	private FS fs;

	@Inject
	public TemplateImpl(Context context, FS fs) {
		super();
		this.context = context;
		this.fs = fs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.template.Template#merge()
	 */
	@Override
	public Optional<String> merge(String templateName) {
		try {
			String merge = JtwigTemplate.classpathTemplate(String.format("/templates/%s", templateName)).render(this.createJtwigModel());
			return Optional.of(merge);
		} catch (ResourceNotFoundException e) {
			System.out.println(Colorize.red("Erro: " + e.getMessage()));
		}
		return Optional.empty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.template.Template#mergeAndCreateFile(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void mergeAndCreateFile(String templateName, MavenFolder mf, String path) {
		Optional<String> content = this.merge(templateName);
		if (content.isPresent()) {
			this.fs.createFile(content.get(), mf, path);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.Template#mergeAndCreateFile(java.lang.String,
	 * sk.api.enums.MavenFolder, sk.api.model.EJavaPackage, java.lang.String)
	 */
	@Override
	public void mergeAndCreateFile(String templateName, MavenFolder mf, EJavaPackage eJavaPackage, String path) {
		Optional<String> content = this.merge(templateName);
		if (content.isPresent()) {
			this.fs.createFile(content.get(), mf, eJavaPackage, path);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.Template#mergeAndAppendFile(java.lang.String,
	 * sk.api.enums.MavenFolder, java.lang.String)
	 */
	@Override
	public void mergeAndAppendFile(String templateName, MavenFolder mf, String path) {
		Optional<String> content = this.merge(templateName);
		if (content.isPresent()) {
			this.fs.appendFile(content.get(), mf, path);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.Template#mergeAndAppendFile(java.lang.String,
	 * sk.api.enums.MavenFolder, sk.api.model.EJavaPackage, java.lang.String)
	 */
	@Override
	public void mergeAndAppendFile(String templateName, MavenFolder mf, EJavaPackage eJavaPackage, String path) {
		Optional<String> content = this.merge(templateName);
		if (content.isPresent()) {
			this.fs.appendFile(content.get(), mf, eJavaPackage, path);
		}
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
