package sk.impl.model;

import java.util.Arrays;

import com.thoughtworks.qdox.model.JavaMethod;

import sk.api.model.EJavaMethod;
import sk.api.model.EJavaProject;

public class EJavaMethodImpl implements EJavaMethod {

	private static final long serialVersionUID = 1L;

	private EJavaProject project;

	private String name;

	private JavaMethod qdoxJavaMethod;

	public EJavaMethodImpl(EJavaProject project, JavaMethod qdoxJavaMethod) {
		super();
		this.project = project;
		this.qdoxJavaMethod = qdoxJavaMethod;
		this.name = this.qdoxJavaMethod.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaMethod#getName()
	 */
	@Override
	public String getMethodName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaMethod#getQdoxJavaMethod()
	 */
	@Override
	public JavaMethod getQdoxJavaMethod() {
		return qdoxJavaMethod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaMethod#hasAnnotationByName(java.lang.String)
	 */
	@Override
	public boolean hasAnnotationByName(String annotationName) {
		//@formatter:off
		return Arrays.asList(this.qdoxJavaMethod.getAnnotations())
				.stream()
				.anyMatch(annotation -> annotation.getType().getValue().endsWith(name));
		//@formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaMethod#getProject()
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
		EJavaMethodImpl other = (EJavaMethodImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EJavaMethod [name=" + name + "]";
	}

}
