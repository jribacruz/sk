package sk.api.util;

import java.io.Serializable;

import sk.api.enums.MavenFolder;

/**
 * Classe utilitária para sistemas de arquivos.
 * 
 * @author jcruz
 *
 */
public interface FS extends Serializable {

	/**
	 * 
	 * Cria um arquivo.
	 * 
	 * @param content
	 * @param mf
	 * @param path
	 */
	public void createFile(String content, MavenFolder mf, String path);

}
