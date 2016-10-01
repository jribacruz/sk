package sk.api.model;

import java.io.Serializable;

import com.thoughtworks.qdox.model.JavaMethod;

import sk.api.reader.Selectable;

/**
 * 
 * @author jcruz
 *
 */
public interface EJavaMethod extends Selectable<EJavaMethod>, Serializable {

	/**
	 * 
	 * @return
	 */
	String getMethodName();

	/**
	 * 
	 * @return
	 */
	JavaMethod getQdoxJavaMethod();

	/**
	 * 
	 * @param annotationName
	 * @return
	 */
	boolean hasAnnotationByName(String annotationName);

	/**
	 * 
	 * @return
	 */
	EJavaProject getEJavaProject();

	@Override
	default int compareTo(EJavaMethod o) {
		return getMethodName().compareTo(o.getMethodName());
	}

	@Override
	default String getConsoleLabel() {
		return getMethodName();
	}

}
