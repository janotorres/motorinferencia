package br.furb.motorinferencia;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.furb.motorinferencia.motor.MotorInferencia;
import br.furb.motorinferencia.objetos.Regra;

public class DOMParserDemo {

	public static void main(String[] args) throws Exception {
		MotorInferencia motorInferencia = new MotorInferencia();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(ClassLoader.getSystemResourceAsStream("br/furb/motorinferencia/xml/regra.xml"));
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node regraNode = nodeList.item(i);
			if (regraNode instanceof Element) {
				NodeList childNodes = regraNode.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					Regra regra = motorInferencia.novaRegra();
					Node cNode = childNodes.item(j);
					if (cNode instanceof Element) {
						String variavel = null;
						String valor = null;
						switch (cNode.getNodeName()) {
						case "se":
							System.out.println("entrou no se");
							
							for (int l = 0; l < cNode.getChildNodes().getLength(); l++) {
								Node node2 = cNode.getChildNodes().item(l);
								if (node2.getNodeName().equals("variavel")){
									variavel = node2.getLastChild().getTextContent().trim();
								} else if (node2.getNodeName().equals("valor")){
									valor = node2.getLastChild().getTextContent().trim();
								}
							}
							regra.se(variavel, valor);
							break;
						case "e":
							System.out.println("entrou no e");
							for (int l = 0; l < cNode.getChildNodes().getLength(); l++) {
								Node node2 = cNode.getChildNodes().item(l);
								if (node2.getNodeName().equals("variavel")){
									variavel = node2.getLastChild().getTextContent().trim();
								} else if (node2.getNodeName().equals("valor")){
									valor = node2.getLastChild().getTextContent().trim();
								}
							}
							System.out.println(variavel);
							System.out.println(valor);
							break;
						case "entao":
							System.out.println("entrou no entao");
							break;
						}
					}
				}
			}

		}


	}
}