package sk.impl;

import java.io.IOException;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.resource.exceptions.ResourceNotFoundException;

import sk.api.Context;
import sk.api.FS;
import sk.api.Template;
import sk.api.model.EPath;
import sk.api.util.Log;

public class TemplateImpl implements Template {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Context context = Beans.getReference(Context.class);

	private FS fs = Beans.getReference(FS.class);

	private Log log = Beans.getReference(Log.class);

	/*
	 * (non-Javadoc)
	 * @see sk4j.template.Template#merge()
	 */
	@Override
	public String merge(String templateName) {
		return JtwigTemplate.classpathTemplate(String.format("/templates/%s.jtwig", templateName)).render(this.createJtwigModel());
	}

	/*
	 * (non-Javadoc)
	 * @see sk4j.template.Template#mergeAndCreateFile(java.lang.String, java.lang.String)
	 */
	@Override
	public void mergeAndCreateFile(String templateName, EPath epath) throws IOException {
		try {
			String content = this.merge(templateName);
			fs.mkdir(fs.getEpath(epath.getPath().getParent()));
			fs.write(epath.getPath(), content);
		} catch (ResourceNotFoundException e) {
			log.error("Template não existe: %s ", templateName);
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
