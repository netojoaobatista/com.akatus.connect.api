package com.akatus.connect.api.v1;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Classe base para criação d classes que lidam com requisições e respostas XML.
 * 
 * @author João Batista Neto
 */
public abstract class AkatusXMLRequestResponseBuilder {
	protected Document document;

	/**
	 * Cria uma instância de {@link Document} para ser utilizado pelas classes
	 * derivadas.
	 * 
	 * @param xmlContent
	 *            é uma {@link String} que, caso não seja null, será utilizada
	 *            na criação do {@link Document}.
	 */
	protected void createDocument(String xmlContent) {
		try {
			final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			final DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();

			if (xmlContent == null) {
				document = documentBuilder.newDocument();
			} else {
				document = documentBuilder.parse(new InputSource(
						new StringReader(xmlContent)));
			}
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria uma instância de {@link Document} para ser utilizado pelas classes
	 * derivadas.
	 */
	protected void createDocument() {
		createDocument(null);
	}
}