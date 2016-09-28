package sk.impl.util;

import org.apache.commons.io.FilenameUtils;

import sk.api.Context;
import sk.api.enums.MavenFolder;
import sk.api.model.EJavaProject;
import sk.api.util.FS;
import sk.impl.Beans;

public class FSImpl implements FS {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EJavaProject eJavaProject = Beans.getReference(EJavaProject.class);

	private Context context = Beans.getReference(Context.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.util.FS#createFile(java.lang.String,
	 * sk.api.enums.MavenFolder, java.lang.String)
	 */
	@Override
	public void createFile(String content, MavenFolder mf, String path) {
		String finalPath = FilenameUtils.normalize(eJavaProject.getPathName().concat(mf.getPath()).concat(context.replace(path)));
		// Files.cre
	}

}
