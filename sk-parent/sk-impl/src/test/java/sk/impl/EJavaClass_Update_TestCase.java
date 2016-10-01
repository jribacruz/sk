package sk.impl;

import java.io.File;
import java.util.Optional;

import sk.api.enums.MavenFolder;
import sk.api.model.EJavaClass;
import sk.api.model.EJavaProject;
import sk.impl.model.EJavaProjectImpl;

public class EJavaClass_Update_TestCase {
	public static void main(String[] args) {
		EJavaProject javaProject = new EJavaProjectImpl(new File("/opt/workspace-luna/aelis2016/"));
		//@formatter:off
		Optional<EJavaClass> javaClass = javaProject.getEJavaClasses(MavenFolder.SRC_MAIN_JAVA).stream()
			.filter(javaClass1 -> javaClass1.getClassName().equals("AtividadeDAO"))
			.findFirst();
		//@formatter:on
		if (javaClass.isPresent()) {
			//@formatter:off
			javaClass.get().update(javaClassSource -> 
				javaClassSource.addMethod()
				.setPublic()
				.setName("isEmpty")
				.setReturnType("boolean")
				.setBody("TypedQuery<Long> q = getEntityManager().createQuery(\"select count(cv.id) from" +javaClass.get().getClassName()+ "cv\", Long.class);"
						+ "\nreturn q.setMaxResults(1).getSingleResult()==0?true:false;")
			);
			//@formatter:on
		}
	}
}
