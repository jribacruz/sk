package sk.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import sk.impl.util.XMLParser;

public class XMLParserTestCase {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		XMLParser parser = new XMLParser(new File("src/test/java/resources/persistence.xml"), false);

		//@formatter:off
		parser.getNodesByXPathExpression("//property")
			.stream()
			.forEach(p -> System.out.println(p.getAttributes().getNamedItem("value").getTextContent()));
		//@formatter:on
	}
}
