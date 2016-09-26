package sk.impl;

import java.io.File;

import sk.api.enums.MavenFolder;
import sk.api.model.EJavaProject;
import sk.impl.model.EJavaProjectImpl;

public class EJavaProjectTestCase {
	public static void main(String[] args) {
		EJavaProject javaProject = new EJavaProjectImpl(new File("/opt/workspace-luna/aelis2016"));
		//@formatter:off
		javaProject.getEJavaClasses(MavenFolder.SRC_MAIN_JAVA).stream()
			.forEach(javaClass -> System.out.println(javaClass.getClassName()));
		//@formatter:on
	}
}
