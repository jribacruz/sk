package sk.api.model;

import java.io.Serializable;
import java.util.Optional;
import java.util.SortedSet;

import com.thoughtworks.qdox.model.JavaClass;

import sk.api.reader.Selectable;
import sk.api.util.Colorize;

/**
 * 
 * @author jcruz
 *
 */
public interface EJavaClass extends Selectable<EJavaClass>, Serializable {

	/**
	 * 
	 * @return
	 */
	String getClassName();

	/**
	 * 
	 * @return
	 */
	String getFullyQualifiedName();

	/**
	 * 
	 * @return
	 */
	String getSourceFolderName();

	/**
	 * 
	 * @return
	 */
	String getPathName();

	/**
	 * 
	 * @return
	 */
	String getPackageName();

	/**
	 * 
	 * @return
	 */
	EJavaPackage getEJavaPackage();

	/**
	 * 
	 * @return
	 */
	Optional<String> getParentPackageName();

	/**
	 * 
	 * @return
	 */
	JavaClass getQdoxJavaClass();

	/**
	 * 
	 * @return
	 */
	SortedSet<EJavaAttribute> getEJavaAttributes();

	/**
	 * 
	 * @return
	 */
	SortedSet<EJavaMethod> getEJavaMethods();

	/**
	 * 
	 * @param index
	 * @return
	 */
	String getSuperClassGenericNameByIndex(int index);

	/**
	 * 
	 * @param genericTypeName
	 * @param index
	 * @return
	 */
	boolean hasSuperClassGenericNameByNameAndIndex(String genericTypeName, int index);

	/**
	 * 
	 * @param annotationName
	 * @return
	 */
	boolean hasAnnotationByName(String annotationName);

	/**
	 * 
	 * @param superClassName
	 * @return
	 */
	boolean extendsSuperClassByName(String superClassName);

	/**
	 * 
	 * @param interfaceName
	 * @return
	 */
	boolean implementsInterfaceByName(String interfaceName);

	/**
	 * 
	 * @return
	 */
	boolean isAbstract();

	@Override
	default int compareTo(EJavaClass ejavaClass) {
		return getClassName().compareTo(ejavaClass.getClassName());
	}

	@Override
	default String getConsoleLabel() {
		return this.getClassName() + " - " + Colorize.gray(this.getQdoxJavaClass().getPackageName());
	}

}
