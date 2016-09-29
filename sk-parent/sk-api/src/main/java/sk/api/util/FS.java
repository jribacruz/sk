package sk.api.util;

import java.io.Serializable;

import sk.api.enums.MavenFolder;
import sk.api.model.EJavaPackage;

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
	 */
	public void createFile(String content, MavenFolder mf);

	/**
	 * 
	 * Cria um arquivo.
	 * 
	 * @param content
	 * @param mf
	 * @param path
	 */
	public void createFile(String content, MavenFolder mf, String path);

	/**
	 * Cria um arquivo.
	 * 
	 * @param content
	 * @param mf
	 * @param eJavaPackage
	 * @param path
	 */
	public void createFile(String content, MavenFolder mf, EJavaPackage eJavaPackage, String path);

	/**
	 * Adiciona o content a um arquivo existente.
	 * 
	 * @param content
	 * @param mf
	 */
	public void appendFile(String content, MavenFolder mf);

	/**
	 * Adiciona o content a um arquivo existente.
	 * 
	 * @param content
	 * @param mf
	 * @param path
	 */
	public void appendFile(String content, MavenFolder mf, String path);

	/**
	 * Adiciona o content a um arquivo existente.
	 * 
	 * @param content
	 * @param mf
	 * @param eJavaPackage
	 * @param path
	 */
	public void appendFile(String content, MavenFolder mf, EJavaPackage eJavaPackage, String path);

	/**
	 * Cria um diretório.
	 * 
	 * @param mf
	 * @param path
	 */
	public void mkdir(MavenFolder mf, String path);

	/**
	 * 
	 * @param mf
	 * @param eJavaPackage
	 * @param path
	 */
	public void mkdir(MavenFolder mf, EJavaPackage eJavaPackage, String path);
}
