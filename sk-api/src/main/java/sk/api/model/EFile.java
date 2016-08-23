package sk.api.model;

import java.io.File;

import sk.api.Colorize;
import sk.api.reader.Selectable;

public interface EFile extends Selectable<EFile> {

	File getFile();

	@Override
	default int compareTo(EFile o) {
		return getFile().getName().compareTo(o.getFile().getName());
	}

	@Override
	default String getConsoleLabel() {
		return Colorize.bold(getFile().getName()) + " - " + Colorize.gray(getFile().getAbsolutePath());
	}

}
