package sk.api.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.SortedSet;

import sk.api.enums.MavenFolder;

/**
 * 
 * Representação de um projeto java.
 * 
 * @author jcruz
 *
 */
public interface EJavaProject extends Serializable {

	/**
	 * Retorna o nome do projeto.
	 * 
	 * @return Nome do projeto
	 */
	String getProjectName();

	/**
	 * Retorna o caminho do projeto.
	 * 
	 * @return Caminho do projeto.
	 */
	String getPathName();

	/**
	 * Retorna o arquivo do projeto java.
	 * 
	 * @return
	 */
	File getProjectFile();

	/**
	 * 
	 * Lista as classes do Maven Folder correspondente.
	 * 
	 * @param mf
	 *            MavenFolder
	 * @return Lista ordenada das classes do MavenFolder correspondete.
	 */
	SortedSet<EJavaClass> getEJavaClasses(MavenFolder mf);

	/**
	 * 
	 * Lista todos os pacotes do Maven Folder correspondente.
	 * 
	 * @param mf
	 * @return
	 */
	SortedSet<EJavaPackage> getEJavaPackages(MavenFolder mf);

	/**
	 * Verifica a existência de uma classe no maven folder especificado.
	 * 
	 * @param javaClassName
	 *            Nome da classe.
	 * @return
	 * @throws IOException
	 */
	boolean hasEJavaClassByName(MavenFolder mf, String javaClassName);

	/**
	 * 
	 * Retorna a unidade de persistência do maven folder indicado..
	 * 
	 * @param mf
	 * @return
	 */
	public Optional<EPersistence> getEPersistence(MavenFolder mf);

	/**
	 * 
	 * Verifica se o project
	 * 
	 * @return
	 */
	public boolean isMavenProject();

}
