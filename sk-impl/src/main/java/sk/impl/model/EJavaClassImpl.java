package sk.impl.model;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.qdox.model.JavaClass;

import sk.api.model.EJavaAttribute;
import sk.api.model.EJavaClass;
import sk.api.model.EJavaMethod;
import sk.api.model.EJavaPackage;
import sk.api.model.EJavaProject;

public class EJavaClassImpl implements EJavaClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EJavaProject project;

	private String name;

	private String fullyQualifiedName;

	private String sourceFolderName;

	private String path;

	private String packageName;

	private String parentPackageName;

	private JavaClass qdoxJavaClass;

	private SortedSet<EJavaAttribute> javaAttributes;

	private SortedSet<EJavaMethod> javaMethods;

	public EJavaClassImpl(EJavaProject project, String sourceFolder, JavaClass qdoxJavaClass) {
		super();
		this.project = project;
		this.sourceFolderName = sourceFolder;
		this.qdoxJavaClass = qdoxJavaClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getName()
	 */
	@Override
	public String getClassName() {
		if (this.name == null) {
			this.name = this.qdoxJavaClass.getName();
		}
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getFullyQualifiedName()
	 */
	@Override
	public String getFullyQualifiedName() {
		if (this.fullyQualifiedName == null) {
			this.fullyQualifiedName = this.qdoxJavaClass.getFullyQualifiedName();
		}
		return fullyQualifiedName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getPath()
	 */
	@Override
	public String getPathName() {
		if (this.path == null) {
			String packageDir = this.qdoxJavaClass.getPackage().getName().replaceAll("\\.", "/");
			this.path = FilenameUtils.normalize(project.getPathName().concat(this.sourceFolderName).concat("/").concat(packageDir));
		}
		return path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getPackageName()
	 */
	@Override
	public String getPackageName() {
		if (this.packageName == null) {
			this.packageName = this.qdoxJavaClass.getPackageName();
		}
		return packageName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getParentPackageName()
	 */
	@Override
	public String getParentPackageName() {
		if (this.parentPackageName == null) {
			List<String> packageTokens = Arrays.asList(getQdoxJavaClass().getPackageName().split("\\."));
			this.parentPackageName = StringUtils.join(packageTokens.subList(0, packageTokens.size() - 1), ".");
		}
		return parentPackageName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getQdoxJavaClass()
	 */
	@Override
	public JavaClass getQdoxJavaClass() {
		return qdoxJavaClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getJavaAttributes()
	 */
	@Override
	public SortedSet<EJavaAttribute> getEJavaAttributes() {
		//@formatter:off
		if (this.javaAttributes == null) {
			this.javaAttributes = Arrays.asList(qdoxJavaClass.getFields())
										.stream()
										.map(javaField -> new EJavaAttributeImpl(project,javaField))
										.collect(Collectors.toCollection(TreeSet::new));
		}
		//@formatter:on
		return javaAttributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getJavaMethods()
	 */
	@Override
	public SortedSet<EJavaMethod> getEJavaMethods() {
		//@formatter:off
		if (this.javaMethods == null) {
			this.javaMethods = Arrays.asList(qdoxJavaClass.getMethods())
									 .stream()
									 .map(javaMethod -> new EJavaMethodImpl(project,javaMethod))
									 .collect(Collectors.toCollection(TreeSet::new));
		}
		//@formatter:on
		return javaMethods;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getJavaPackage()
	 */
	@Override
	public EJavaPackage getEJavaPackage() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#hasAnnotationByName(java.lang.String)
	 */
	@Override
	public boolean hasAnnotationByName(String name) {
		//@formatter:off
		return Arrays.asList(qdoxJavaClass.getAnnotations())
					.stream()
					.anyMatch(p -> p.getType().getValue().endsWith(name));
		//@formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getSourceFolderName()
	 */
	@Override
	public String getSourceFolderName() {
		return this.sourceFolderName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#getSuperClassGenericNameByIndex(int)
	 */
	@Override
	public String getSuperClassGenericNameByIndex(int index) {
		if (index <= qdoxJavaClass.getSuperClass().getActualTypeArguments().length) {
			return qdoxJavaClass.getSuperClass().getActualTypeArguments()[index].getValue();
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#hasSuperClassGenericNameByNameAndIndex(java.lang.String, int)
	 */
	@Override
	public boolean hasSuperClassGenericNameByNameAndIndex(String genericTypeName, int index) {
		return getSuperClassGenericNameByIndex(index).endsWith(genericTypeName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#isAbstract()
	 */
	@Override
	public boolean isAbstract() {
		return this.qdoxJavaClass.isAbstract();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#extendsSuperClassByName(java.lang.String)
	 */
	@Override
	public boolean extendsSuperClassByName(String name) {
		return this.qdoxJavaClass.getSuperClass().getValue().endsWith(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaClass#implementsInterfaceByName(java.lang.String)
	 */
	@Override
	public boolean implementsInterfaceByName(String name) {
		//@formatter:off
		return Arrays.asList(this.qdoxJavaClass.getImplementedInterfaces())
			.stream()
			.anyMatch(javaClass -> javaClass.getName().endsWith(name));
		//@formatter:on
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getFullyQualifiedName() == null) ? 0 : getFullyQualifiedName().hashCode());
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
		EJavaClassImpl other = (EJavaClassImpl) obj;
		if (getFullyQualifiedName() == null) {
			if (other.getFullyQualifiedName() != null)
				return false;
		} else if (!getFullyQualifiedName().equals(other.getFullyQualifiedName()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EJavaFile [fullyQualifiedName=" + getFullyQualifiedName() + "]";
	}

}
