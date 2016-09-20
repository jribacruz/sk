package sk.api.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.SortedSet;

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

	SortedSet<EJavaClass> getAllEJavaClasses() throws IOException;

	SortedSet<EJavaClass> getMainEJavaClasses();

	SortedSet<EJavaPackage> getMainEJavaPackages();

	SortedSet<EJavaPackage> getTestEJavaPackages();

	SortedSet<EJavaClass> getTestEJavaClasses();

	boolean hasMainEJavaClassByName(String javaClassName) throws IOException;

	public boolean isMavenProject();

}
