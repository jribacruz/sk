package sk.api;

import java.io.IOException;
import java.io.Serializable;

import sk.api.enums.MavenFolder;
import sk.api.model.EJavaPackage;

/**
 * 
 * @author jcruz
 *
 */
public interface Template extends Serializable {

	/**
	 * 
	 * Mescla o arquivo de template com as variáveis do contexto.
	 * 
	 * @param templateName
	 *            Nome do template.
	 * @return Dados mesclados.
	 */
	String merge(String templateName);

	/**
	 * Mescla o arquivo de template com as variáveis do contexto gerando o arquivo correpondente.
	 * 
	 * @param templateName
	 *            Nome do template.
	 * @param path
	 *            Caminho do arquivo.
	 * @throws IOException
	 */
	void mergeAndCreateFile(String templateName, MavenFolder mf, String path);

	/**
	 * Mescla o arquivo de template com as variáveis do contexto gerando o arquivo correpondente.
	 * 
	 * @param templateName
	 * @param mf
	 * @param eJavaPackage
	 * @param path
	 * @throws IOException
	 */
	void mergeAndCreateFile(String templateName, MavenFolder mf, EJavaPackage eJavaPackage, String path);

	/**
	 * Mescla o arquivo de template com as variáveis do contexto gerando o arquivo correpondente.
	 * 
	 * @param templateName
	 *            Nome do template.
	 * @param path
	 *            Caminho do arquivo.
	 * @throws IOException
	 */
	void mergeAndAppendFile(String templateName, MavenFolder mf, String path);

	/**
	 * Mescla o arquivo de template com as variáveis do contexto gerando o arquivo correpondente.
	 * 
	 * @param templateName
	 * @param mf
	 * @param eJavaPackage
	 * @param path
	 * @throws IOException
	 */
	void mergeAndAppendFile(String templateName, MavenFolder mf, EJavaPackage eJavaPackage, String path);

}
