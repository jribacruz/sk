package sk.api.model;

import java.io.Serializable;

import com.thoughtworks.qdox.model.JavaField;

import sk.api.reader.Selectable;
import sk.api.util.Colorize;

/**
 * Modelo de atributo java.
 * 
 * @author jcruz
 *
 */
public interface EJavaAttribute extends Selectable<EJavaAttribute>, Serializable {

	/**
	 * Retorna o nome do atributo java.
	 * 
	 * @return Nome do atributo java.
	 */
	String getAttributeName();

	/**
	 * Retorna objeto qdox do atributo.
	 * 
	 * @return objeto do tipo JavaField qdox.
	 */
	JavaField getQdoxJavaField();

	/**
	 * 
	 * @return
	 */
	boolean isLongPrimitive();

	/**
	 * 
	 * @return
	 */
	boolean isLongWrapper();

	/**
	 * 
	 * @return
	 */
	boolean isIntegerPrimitive();

	/**
	 * 
	 * @return
	 */
	boolean isIntegerWrapper();

	/**
	 * 
	 * @return
	 */
	boolean isBigDecimal();

	/**
	 * 
	 * @return
	 */
	boolean isDate();

	/**
	 * 
	 * @return
	 */
	boolean isBooleanPrimitive();

	/**
	 * 
	 * @return
	 */
	boolean isBooleanWrapper();

	/**
	 * 
	 * @return
	 */
	boolean isString();

	/**
	 * 
	 * @return
	 */
	boolean isList();

	/**
	 * 
	 * @return
	 */
	boolean isMap();

	/**
	 * 
	 * @return
	 */
	boolean isSet();

	/**
	 * 
	 * @return
	 */
	boolean isEnum();

	/**
	 * 
	 * @return
	 */
	boolean isStatic();

	/**
	 * 
	 * @return
	 */
	boolean isPrivate();

	/**
	 * 
	 * @return
	 */
	boolean isPublic();

	/**
	 * 
	 * @return
	 */
	boolean isProtected();

	/**
	 * 
	 * @param annotationName
	 * @return
	 */
	boolean hasAnnotationByName(String annotationName);

	/**
	 * 
	 * @param genericTypeName
	 * @param index
	 * @return
	 */
	boolean hasGenericNameByNameAndIndex(String genericTypeName, int index);

	/**
	 * 
	 * @param index
	 * @return
	 */
	String getGenericNameByIndex(int index);

	/**
	 * 
	 * @return
	 */
	EJavaProject getEJavaProject();

	@Override
	default int compareTo(EJavaAttribute o) {
		return getAttributeName().compareTo(o.getAttributeName());
	}

	@Override
	default String getConsoleLabel() {
		return this.getAttributeName() + " - " + Colorize.gray(this.getQdoxJavaField().getType().getValue());
	}

}
