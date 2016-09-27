package sk.impl.model;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import sk.api.enums.MavenFolder;
import sk.api.model.EJavaProject;
import sk.api.model.EPersistence;
import sk.impl.util.XMLParser;

public class EPersistenceImpl implements EPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private XMLParser xmlParser;

	private EJavaProject eJavaProject;

	private MavenFolder mavenFolder;

	public EPersistenceImpl(EJavaProject eJavaProject, MavenFolder mavenFolder)
			throws SAXException, IOException, ParserConfigurationException {
		super();
		this.eJavaProject = eJavaProject;
		this.mavenFolder = mavenFolder;
		read();
	}

	private void read() throws SAXException, IOException, ParserConfigurationException {
		String persistenceXMLPath = FilenameUtils
				.normalize(eJavaProject.getPathName().concat(mavenFolder.getPath()).concat("META-INF/persistence.xml"));
		this.xmlParser = new XMLParser(new File(persistenceXMLPath));
	}

	@Override
	public String getPersistenceUnitName() {
		Optional<Node> node = xmlParser.getNodeByXPathExpression("//persistence-unit");
		if (node.isPresent()) {
			return node.get().getAttributes().getNamedItem("name").getNodeValue();
		}
		return "";
	}

	@Override
	public String getPersistenceUnitTransactionType() {
		Optional<Node> node = xmlParser.getNodeByXPathExpression("//persistence-unit");
		if (node.isPresent()) {
			return node.get().getAttributes().getNamedItem("transaction-type").getNodeValue();
		}
		return "";
	}

	@Override
	public Set<String> getClasses() {
		//@formatter:off
		return xmlParser.getNodesByXPathExpression("//class")
			.stream()
			.map(node -> node.getTextContent())
			.collect(Collectors.toSet());
		//@formatter:on
	}

	@Override
	public Map<String, String> getProperties() {
		//@formatter:off
		return xmlParser.getNodesByXPathExpression("//property")
			.stream()
			.collect(
					Collectors.toMap(
							node -> node.getAttributes().getNamedItem("name").getNodeValue(), 
							node -> node.getAttributes().getNamedItem("value").getNodeValue()
							)
					);
		//@formatter:on
	}

}
