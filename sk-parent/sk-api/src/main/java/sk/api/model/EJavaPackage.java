package sk.api.model;

import java.io.Serializable;

import com.thoughtworks.qdox.model.JavaPackage;

import sk.api.reader.Selectable;

/**
 * 
 * @author jcruz
 *
 */
public interface EJavaPackage extends Selectable<EJavaPackage>, Serializable {

	/**
	 * 
	 * @return
	 */
	String getPackageName();

	/**
	 * 
	 * @return
	 */
	String getSourceFolderName();

	/**
	 * 
	 * @return
	 */
	JavaPackage getQdoxJavaPackage();

	/**
	 * 
	 * @return
	 */
	String getPathName();

	/**
	 * 
	 * @return
	 */
	EJavaProject getEJavaProject();

	@Override
	default int compareTo(EJavaPackage o) {
		return getPackageName().compareTo(o.getPackageName());
	}

	@Override
	default String getConsoleLabel() {
		return getPackageName();
	}

}
