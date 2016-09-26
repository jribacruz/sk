package sk.api.enums;

/**
 * 
 * Layout de diretório de projeto Maven.
 * 
 * @author jcruz
 *
 */
public enum MavenFolder {
	/**
	 * 
	 */
	SRC_MAIN_JAVA("/src/main/java/"),
	/**
	 * 
	 */
	SRC_MAIN_RESOURCES("/src/main/resources/"),
	/**
	 * 
	 */
	SRC_TEST_JAVA("/src/test/java/"),

	/**
	 * 
	 */
	SRC_TEST_RESOURCES("/src/test/resources/"),

	/**
	 * 
	 */
	SRC_MAIN_WEBAPP("/src/main/webapp/"),

	/**
	 * 
	 */
	SRC_SITE("/src/site/"),

	/**
	 * 
	 */
	TARGET("/target/");

	private String path;

	private MavenFolder(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
