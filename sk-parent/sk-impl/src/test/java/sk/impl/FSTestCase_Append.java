package sk.impl;

import java.io.File;

import sk.api.Context;
import sk.api.enums.MavenFolder;
import sk.api.model.EJavaProject;
import sk.api.util.FS;
import sk.impl.model.EJavaProjectImpl;
import sk.impl.util.FSImpl;

public class FSTestCase_Append {

	public static void main(String[] args) {
		EJavaProject eJavaProject = new EJavaProjectImpl(new File("/opt/workspace-luna/aelis2016"));
		Context context = new ContextImpl();

		FS fs = new FSImpl(eJavaProject, context);

		fs.appendFile("Aqui denovo", MavenFolder.SRC_MAIN_JAVA, "Teste.java");
	}

}
