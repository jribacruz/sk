package sk.impl.model;

import java.util.Arrays;

import com.thoughtworks.qdox.model.JavaField;

import sk.api.model.EJavaAttribute;
import sk.api.model.EJavaProject;

public class EJavaAttributeImpl implements EJavaAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7454896544844888494L;

	private EJavaProject project;

	private String name;

	private JavaField qdoxJavaField;

	public EJavaAttributeImpl(EJavaProject project, JavaField qdoxJavaField) {
		super();
		this.project = project;
		this.qdoxJavaField = qdoxJavaField;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#getName()
	 */
	@Override
	public String getAttributeName() {
		return this.qdoxJavaField.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#getQdoxJavaField()
	 */
	@Override
	public JavaField getQdoxJavaField() {
		return qdoxJavaField;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#hasAnnotationByName(java.lang.String)
	 */
	@Override
	public boolean hasAnnotationByName(String name) {
		//@formatter:off
		return Arrays.asList(this.qdoxJavaField.getAnnotations())
					.stream()
					.anyMatch(p -> p.getType().getValue().endsWith(name));
		//@formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isLongPrimitive()
	 */
	@Override
	public boolean isLongPrimitive() {
		return this.qdoxJavaField.getType().getValue().equals("long");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isIntegerPrimitive()
	 */
	@Override
	public boolean isIntegerPrimitive() {
		return this.qdoxJavaField.getType().getValue().equals("int");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isBigDecimal()
	 */
	@Override
	public boolean isBigDecimal() {
		return this.qdoxJavaField.getType().getValue().endsWith("BigDecimal");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isDate()
	 */
	@Override
	public boolean isDate() {
		return this.qdoxJavaField.getType().getValue().endsWith("Date");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isBooleanPrimitive()
	 */
	@Override
	public boolean isBooleanPrimitive() {
		return this.qdoxJavaField.getType().getValue().equals("boolean");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isLongWrapper()
	 */
	@Override
	public boolean isLongWrapper() {
		return this.qdoxJavaField.getType().getValue().endsWith("Long");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isIntegerWrapper()
	 */
	@Override
	public boolean isIntegerWrapper() {
		return this.qdoxJavaField.getType().getValue().endsWith("Integer");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isBooleanWrapper()
	 */
	@Override
	public boolean isBooleanWrapper() {
		return this.qdoxJavaField.getType().getValue().endsWith("Boolean");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isString()
	 */
	@Override
	public boolean isString() {
		return this.qdoxJavaField.getType().getValue().endsWith("String");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isList()
	 */
	@Override
	public boolean isList() {
		return this.qdoxJavaField.getType().getValue().endsWith("List");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isMap()
	 */
	@Override
	public boolean isMap() {
		return this.qdoxJavaField.getType().getValue().endsWith("Map");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isSet()
	 */
	@Override
	public boolean isSet() {
		return this.qdoxJavaField.getType().getValue().endsWith("Set");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isEnum()
	 */
	@Override
	public boolean isEnum() {
		if (qdoxJavaField.getType().getJavaClass() != null) {
			if (qdoxJavaField.getType().getJavaClass().getSuperClass() != null) {
				return qdoxJavaField.getType().getJavaClass().getSuperClass().getFullyQualifiedName().endsWith("Enum");
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isStatic()
	 */
	@Override
	public boolean isStatic() {
		return this.qdoxJavaField.isStatic();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isPrivate()
	 */
	@Override
	public boolean isPrivate() {
		return this.qdoxJavaField.isPrivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isPublic()
	 */
	@Override
	public boolean isPublic() {
		return this.qdoxJavaField.isPublic();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#isProtected()
	 */
	@Override
	public boolean isProtected() {
		return this.qdoxJavaField.isProtected();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#hasGenericNameByNameAndIndex(java.lang.String, int)
	 */
	@Override
	public boolean hasGenericNameByNameAndIndex(String genericTypeName, int index) {
		if (qdoxJavaField.getType().getActualTypeArguments().length > 0) {
			if (index <= qdoxJavaField.getType().getActualTypeArguments().length) {
				return qdoxJavaField.getType().getActualTypeArguments()[index].getValue().endsWith(genericTypeName);
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#getGenericNameByIndex(int)
	 */
	@Override
	public String getGenericNameByIndex(int index) {
		if (index <= qdoxJavaField.getType().getActualTypeArguments().length) {
			return qdoxJavaField.getType().getActualTypeArguments()[index].getValue();
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaAttribute#getProject()
	 */
	@Override
	public EJavaProject getEJavaProject() {
		return project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EJavaAttributeImpl other = (EJavaAttributeImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EJavaAttribute [name=" + name + "]";
	}

}
