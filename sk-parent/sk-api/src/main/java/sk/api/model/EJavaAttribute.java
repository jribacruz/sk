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

	boolean isLongPrimitive();

	boolean isLongWrapper();

	boolean isIntegerPrimitive();

	boolean isIntegerWrapper();

	boolean isBigDecimal();

	boolean isDate();

	boolean isBooleanPrimitive();

	boolean isBooleanWrapper();

	boolean isString();

	boolean isList();

	boolean isMap();

	boolean isSet();

	boolean isEnum();

	boolean isStatic();

	boolean isPrivate();

	boolean isPublic();

	boolean isProtected();

	boolean hasAnnotationByName(String annotationName);

	boolean hasGenericNameByNameAndIndex(String genericTypeName, int index);

	String getGenericNameByIndex(int index);

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
