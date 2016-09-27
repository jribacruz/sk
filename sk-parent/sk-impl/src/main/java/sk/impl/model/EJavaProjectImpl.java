package sk.impl.model;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.thoughtworks.qdox.JavaDocBuilder;

import sk.api.enums.MavenFolder;
import sk.api.model.EJavaClass;
import sk.api.model.EJavaPackage;
import sk.api.model.EJavaProject;
import sk.api.model.EPersistence;

public class EJavaProjectImpl implements EJavaProject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private File file;

	private Map<MavenFolder, EPersistence> cachePersistenceMap = new HashMap<>();

	private Multimap<MavenFolder, EJavaClass> cacheEJavaClassesMMap = HashMultimap.create();

	private Multimap<MavenFolder, EJavaPackage> cacheEJavaPackagesMMap = HashMultimap.create();

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
	 * @see sk4j.model.EJavaProject#hasSrcMainJavaClassByName(java.lang.String)
	 */
	@Override
	public boolean hasEJavaClassByName(MavenFolder mf, String name) {
		return this.getEJavaClasses(mf).stream().anyMatch(javaClass -> javaClass.getClassName().equals(name));
	}

	@Override
	public Optional<EPersistence> getEPersistence(MavenFolder mf) {
		if (!cachePersistenceMap.containsKey(mf)) {
			try {
				EPersistence ePersistence = new EPersistenceImpl(this, mf);
				this.cachePersistenceMap.put(mf, ePersistence);
			} catch (SAXException | IOException | ParserConfigurationException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		return Optional.of(this.cachePersistenceMap.get(mf));
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
	 * @see sk.api.model.EJavaProject#getEJavaClasses(sk.api.enums.MavenFolder)
	 */
	@Override
	public SortedSet<EJavaClass> getEJavaClasses(MavenFolder mf) {
		if (!cacheEJavaClassesMMap.containsKey(mf)) {
			// @formatter:off
			SortedSet<EJavaClass> eJavaClasses = getEJavaPackages(mf).stream()
					.map(javaPackage -> javaPackage.getQdoxJavaPackage().getClasses())
					.flatMap(qdoxJavaClasses -> Arrays.asList(qdoxJavaClasses).stream())
					.map(qdoxJavaClass -> new EJavaClassImpl(this, mf.getPath(), qdoxJavaClass))
					.filter(javaClass -> !javaClass.getQdoxJavaClass().isInterface()
							&& !javaClass.getQdoxJavaClass().isEnum())
					.collect(Collectors.toCollection(TreeSet::new));
			// @formatter:on
			cacheEJavaClassesMMap.putAll(mf, eJavaClasses);
		}
		return new TreeSet<>(this.cacheEJavaClassesMMap.get(mf));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.model.EJavaProject#getEJavaPackages(sk.api.enums.MavenFolder)
	 */
	@Override
	public SortedSet<EJavaPackage> getEJavaPackages(MavenFolder mf) {
		if (!cacheEJavaPackagesMMap.containsKey(mf)) {
			JavaDocBuilder builder = new JavaDocBuilder();
			File javaDir = new File(FilenameUtils.normalize(getPathName().concat(mf.getPath())));
			builder.addSourceTree(javaDir);
			// @formatter:off
			SortedSet<EJavaPackage> eJavaPackages = Arrays.asList(builder.getPackages()).stream()
					.map(javaPackage -> new EJavaPackageImpl(this, javaPackage, mf.getPath()))
					.collect(Collectors.toCollection(TreeSet::new));
			//@formatter:on
			this.cacheEJavaPackagesMMap.putAll(mf, eJavaPackages);
		}
		return new TreeSet<>(this.cacheEJavaPackagesMMap.get(mf));
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
