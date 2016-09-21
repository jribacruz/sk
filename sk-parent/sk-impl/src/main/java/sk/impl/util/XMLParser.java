package sk.impl.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParser {

	private File xmlFile;

	public XMLParser(File xmlFile) throws SAXException, IOException, ParserConfigurationException {
		super();
		this.xmlFile = xmlFile;
		init();
	}

	private void init() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(this.xmlFile);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
	}

}
