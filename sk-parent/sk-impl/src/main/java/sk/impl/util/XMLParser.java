package sk.impl.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sk.api.util.Colorize;

public class XMLParser {

	private File xmlFile;

	private XPath xpath;

	private Document doc;

	public XMLParser(File xmlFile) throws SAXException, IOException, ParserConfigurationException {
		super();
		this.xmlFile = xmlFile;
		init();
	}

	private void init() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		this.doc = dBuilder.parse(this.xmlFile);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		this.xpath = xPathfactory.newXPath();
	}

	public List<Node> getNodesByXPathExpression(String expression) {
		List<Node> nodes = new ArrayList<>();
		try {
			NodeList nodeList = (NodeList) this.xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				nodes.add(nodeList.item(i));
			}
		} catch (XPathExpressionException e) {
			System.out.println(Colorize.red("Erro: " + e.getMessage()));
		}
		return nodes;
	}

}
