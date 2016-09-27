package sk.impl;

import java.io.File;

import sk.api.enums.MavenFolder;
import sk.api.model.EJavaProject;
import sk.impl.model.EJavaProjectImpl;

public class EJavaProject_ReadEPersistence_TestCase {
	public static void main(String[] args) {
		EJavaProject javaProject = new EJavaProjectImpl(new File("/opt/workspace-luna/aelis2016"));
		//@formatter:off
		javaProject.getEPersistence(MavenFolder.SRC_MAIN_RESOURCES).get().getClasses()
			.stream()
			.forEach(pclass -> System.out.println(pclass));
	}
}
