package sk.impl.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;

import com.thoughtworks.qdox.JavaDocBuilder;

import sk.api.model.EFile;
import sk.api.model.EJavaClass;
import sk.api.model.EJavaPackage;
import sk.api.model.EJavaProject;
import sk.api.model.EPath;

public class EJavaProjectImpl implements EJavaProject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private File file;

	private SortedSet<EJavaClass> allEJavaClasses;

	private SortedSet<EJavaClass> mainEJavaClasses;

	private SortedSet<EJavaClass> testEJavaClasses;

	private SortedSet<EJavaPackage> mainEJavaPackages;

	private SortedSet<EJavaPackage> testEJavaPackages;

	private SortedSet<EPath> webappEPaths;

	private SortedSet<EFile> webappEFiles;

	private SortedSet<EPath> epaths;

	private SortedSet<EFile> efiles;

	private SortedSet<EFile> webappXHTMLFiles;

	public EJavaProjectImpl(File file) {
		super();
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getName()
	 */
	@Override
	public String getProjectName() {
		if (this.name == null) {
			this.name = FilenameUtils.getBaseName(getPathName());
		}
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getPath()
	 */
	@Override
	public String getPathName() {
		return file.getAbsolutePath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getFile()
	 */
	@Override
	public File getProjectFile() {
		return file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getJavaClasses()
	 */
	@Override
	public SortedSet<EJavaClass> getAllEJavaClasses() throws IOException {
		if (allEJavaClasses == null) {
			this.allEJavaClasses = new TreeSet<>();
			this.allEJavaClasses.addAll(getMainEJavaClasses());
			this.allEJavaClasses.addAll(getTestEJavaClasses());
		}
		return allEJavaClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getDirs()
	 */
	@Override
	public SortedSet<EPath> getEPaths() throws IOException {
		//@formatter:off
		if (this.epaths == null) {
			this.epaths = Files.walk(file.toPath())
							 .filter(p -> p.toFile().isDirectory() && 
									 	 !p.toFile().isHidden() && 
									 	 !p.toFile().getAbsolutePath().contains(".svn") &&
									 	 !p.toFile().getAbsolutePath().contains("/target"))
							 .map(EPathImpl::new)
							 .collect(Collectors.toCollection(TreeSet::new));
		}
		//@formatter:on
		return epaths;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getFiles()
	 */
	@Override
	public SortedSet<EFile> getEFiles() throws IOException {
		if (this.efiles == null) {
			//@formatter:off
			this.efiles = Files.walk(file.toPath())
							  .filter(p -> p.toFile().isFile() && !p.toFile().isHidden())
							  .map(p -> new EFileImpl(p.toFile()))
							  .collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
		}
		return efiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#hasSrcMainJavaClassByName(java.lang.String)
	 */
	@Override
	public boolean hasMainEJavaClassByName(String name) throws IOException {
		//@formatter:off
		return getMainEJavaClasses()
			.stream()
			.anyMatch(javaClass -> javaClass.getClassName().equals(name));
		//@formatter:on
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#isMavenProject()
	 */
	@Override
	public boolean isMavenProject() {
		return new File(String.format("%s/pom.xml", getPathName())).exists();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getSrcMainJavaClasses()
	 */
	@Override
	public SortedSet<EJavaClass> getMainEJavaClasses() {
		if (this.mainEJavaClasses == null) {
			//@formatter:off
			this.mainEJavaClasses = getMainEJavaPackages()
							.parallelStream()
							.map(javaPackage -> javaPackage.getQdoxJavaPackage().getClasses())
							.flatMap(qdoxJavaClasses -> Arrays.asList(qdoxJavaClasses).stream())
							.map(qdoxJavaClass -> new EJavaClassImpl(this, "/src/main/java/", qdoxJavaClass))
							.filter(javaClass -> !javaClass.getQdoxJavaClass().isInterface() && !javaClass.getQdoxJavaClass().isEnum())
							.collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
		}
		return mainEJavaClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getSrcTestJavaClasses()
	 */
	@Override
	public SortedSet<EJavaClass> getTestEJavaClasses() {
		if (this.testEJavaClasses == null) {
			//@formatter:off
			this.testEJavaClasses = getTestEJavaPackages()
							.stream()
							.map(javaPackage -> javaPackage.getQdoxJavaPackage().getClasses())
							.flatMap(qdoxJavaClasses -> Arrays.asList(qdoxJavaClasses).stream())
							.map(qdoxJavaClass -> new EJavaClassImpl(this, "/src/test/java/", qdoxJavaClass))
							.filter(javaClass -> !javaClass.getQdoxJavaClass().isInterface() && !javaClass.getQdoxJavaClass().isEnum())
							.collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
		}
		return testEJavaClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getSrcMainJavaPackages()
	 */
	@Override
	public SortedSet<EJavaPackage> getMainEJavaPackages() {
		if (this.mainEJavaPackages == null) {
			JavaDocBuilder builder = new JavaDocBuilder();
			File srcMainJavaDir = new File(FilenameUtils.normalize(getPathName().concat("/src/main/java/")));
			builder.addSourceTree(srcMainJavaDir);

			//@formatter:off
			this.mainEJavaPackages = Arrays.asList(builder.getPackages())
					.stream()
					.map(javaPackage -> new EJavaPackageImpl(this, javaPackage , "/src/main/java/"))
					.collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
		}
		return this.mainEJavaPackages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getSrcMainWebappDirs()
	 */
	@Override
	public SortedSet<EPath> getWebappEPaths() throws IOException {
		if (this.webappEPaths == null) {
			//@formatter:off
			this.webappEPaths= Files.walk(file.toPath())
					 				.filter(path -> path.toFile().isDirectory() && !path.toFile().isHidden())
					 				.filter(dir -> dir.toFile().getAbsolutePath().contains("/src/main/webapp/"))
					 				.map(EPathImpl::new)
					 				.collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
		}
		return webappEPaths;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getSrcMainWebappFiles()
	 */
	@Override
	public SortedSet<EFile> getWebappEFiles() throws IOException {
		if (this.webappEFiles == null) {
			//@formatter:off
			this.webappEFiles = Files.walk(file.toPath())
					 				.filter(path -> path.toFile().isFile() && !path.toFile().isHidden())
					 				.filter(file -> file.toFile().getAbsolutePath().contains("/src/main/webapp/"))
					 				.map(path -> new EFileImpl(path.toFile()))
					 				.collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
		}
		return webappEFiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getSrcTestJavaPackages()
	 */
	@Override
	public SortedSet<EJavaPackage> getTestEJavaPackages() {
		if (this.testEJavaPackages == null) {
			JavaDocBuilder builder = new JavaDocBuilder();
			File srcTestJavaDir = new File(FilenameUtils.normalize(getPathName().concat("/src/test/java/")));
			builder.addSourceTree(srcTestJavaDir);

			//@formatter:off
			this.testEJavaPackages = Arrays.asList(builder.getPackages())
										.stream()
										.map(javaPackage -> new EJavaPackageImpl(this, javaPackage , "/src/test/java/"))
										.collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
		}
		return this.testEJavaPackages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.model.EJavaProject#getSrcMainWebappXHTMLFiles()
	 */
	@Override
	public SortedSet<EFile> getWebappXHTMLFiles() throws IOException {
		if (this.webappXHTMLFiles == null) {
			//@formatter:off
			this.webappXHTMLFiles = getWebappEFiles()
												.stream()
												.filter(file -> file.getFile().getName().endsWith(".xhtml"))
												.collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
		}
		return webappXHTMLFiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
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
		EJavaProjectImpl other = (EJavaProjectImpl) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EJavaProjectImpl [name=" + name + "]";
	}

}
