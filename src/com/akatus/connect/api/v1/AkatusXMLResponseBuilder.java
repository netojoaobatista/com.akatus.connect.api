package com.akatus.connect.api.v1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.akatus.connect.api.AkatusResponse;
import com.akatus.connect.api.AkatusResponseBuilder;

/**
 * Base para implementação de um construtor do objeto que representa a reposta
 * XML.
 * 
 * @author João Batista Neto
 */
public abstract class AkatusXMLResponseBuilder extends
		AkatusXMLRequestResponseBuilder implements AkatusResponseBuilder {

	public AkatusResponse build(String response) {
		final AkatusResponse akatusResponse = createAkatusResponse();

		createDocument(response);
		readResponse(akatusResponse);

		return akatusResponse;
	}

	/**
	 * Cria uma instância de {@link AkatusResponse} para ser utilizada na
	 * criação do objeto de resposta.
	 * 
	 * @return A instância de {@link AkatusResponse} criada.
	 */
	protected abstract AkatusResponse createAkatusResponse();

	private String getDescription() {
		return getNodeTextValue("description");
	}

	protected String getNodeTextValue(Element element, String nodeName,
			int nodeIndex) {
		String nodeTextValue = null;

		final Node node = element.getElementsByTagName(nodeName)
				.item(nodeIndex);

		if (node != null) {
			nodeTextValue = node.getTextContent();
		}

		return nodeTextValue;
	}

	protected String getNodeTextValue(String nodeName, int nodeIndex) {
		return getNodeTextValue(document.getDocumentElement(), nodeName,
				nodeIndex);
	}

	protected String getNodeTextValue(Element element, String nodeName) {
		return getNodeTextValue(element, nodeName, 0);
	}

	protected String getNodeTextValue(String nodeName) {
		return getNodeTextValue(nodeName, 0);
	}

	private String getStatus() {
		return getNodeTextValue("status");
	}

	protected void readResponse(AkatusResponse akatusResponse) {
		akatusResponse.setStatus(getStatus());
		akatusResponse.setDescription(getDescription());
	}
}