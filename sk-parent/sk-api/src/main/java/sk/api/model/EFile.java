package sk.api.model;

import java.io.File;

import sk.api.reader.Selectable;
import sk.api.util.Colorize;

/**
 * 
 * @author jcruz
 *
 */
public interface EFile extends Selectable<EFile> {

	/**
	 * 
	 * @return
	 */
	File getFile();

	/**
	 * 
	 */
	@Override
	default int compareTo(EFile o) {
		return getFile().getName().compareTo(o.getFile().getName());
	}

	/**
	 * 
	 */
	@Override
	default String getConsoleLabel() {
		return Colorize.bold(getFile().getName()) + " - " + Colorize.gray(getFile().getAbsolutePath());
	}

}
