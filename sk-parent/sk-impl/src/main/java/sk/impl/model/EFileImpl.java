package sk.impl.model;

import java.io.File;

import sk.api.model.EFile;

public class EFileImpl implements EFile {

	private File file;

	public EFileImpl(File file) {
		super();
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EFile#getFile()
	 */
	@Override
	public File getFile() {
		return this.file;
	}

}
