package com.akatus.connect.api.v1;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.akatus.connect.api.AkatusOperation;
import com.akatus.connect.api.AkatusRequestBuilder;
import com.akatus.connect.api.v1.entity.AkatusAccount;

/**
 * Base para implementação de um construtor do corpo da requisição em XML.
 * 
 * @author João Batista Neto
 */
public abstract class AkatusXMLRequestBuilder extends
		AkatusXMLRequestResponseBuilder implements AkatusRequestBuilder {

	/**
	 * Recupera o Content-Type do conteúdo que será construído.
	 */
	public String getContentType() {
		return "text/xml; charset=utf-8";
	}

	/**
	 * Cria uma instância de {@link Element} com um conteúdo texto.
	 * 
	 * @param elementName
	 *            é uma {@link String} com o nome do elemento que será criado.
	 * @param textContent
	 *            é uma {@link String} com o conteúdo texto do elemento que será
	 *            criado.
	 * @return O {@link Element} criado.
	 */
	protected Element createElementWithTextContent(String elementName,
			String textContent) {

		final Element element = document.createElement(elementName);
		element.setTextContent(textContent);

		return element;
	}

	/**
	 * Cria um elemento com os dados da conta (api_key e email) para ser
	 * utilizado na requisição.
	 * 
	 * @param elementName
	 *            é uma {@link String} com o nome do elemento que conterá os nós
	 *            api_key e email.
	 * @param operation
	 *            é a {@link AkatusOperation} que está solicitou a construção do
	 *            XML da requisição.
	 * @return A instância de {@link Element} criada.
	 */
	protected Element createAccountElement(String elementName,
			AkatusOperation operation) {

		final AkatusAccount receiver = operation.getAccount();
		final Element accountElement = document.createElement(elementName);

		accountElement.appendChild(createElementWithTextContent("api_key",
				receiver.getApiKey()));
		accountElement.appendChild(createElementWithTextContent("email",
				receiver.getEmail()));

		return accountElement;
	}

	/**
	 * Transforma a instância de {@link Document} em uma {@link String}.
	 * 
	 * @return A {@link String} com o XML transformado.
	 */
	protected String transformDocument() {
		final StringWriter stringWriter = new StringWriter();

		try {
			final TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			final Transformer transformer = transformerFactory.newTransformer();

			final StreamResult result = new StreamResult(stringWriter);
			final DOMSource source = new DOMSource(document);

			transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
		} catch (final TransformerException e) {
			e.printStackTrace();
		}

		return stringWriter.toString();
	}
}