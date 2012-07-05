package com.akatus.connect.api.v1.paymentmethod;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.akatus.connect.api.AkatusResponse;
import com.akatus.connect.api.v1.AkatusXMLResponseBuilder;
import com.akatus.connect.api.v1.entity.PaymentMethod;

class PaymentMethodsXMLResponseBuilder extends AkatusXMLResponseBuilder {
	protected AkatusResponse createAkatusResponse() {
		return new PaymentMethodsResponse();
	}

	@Override
	protected void readResponse(AkatusResponse akatusResponse) {
		super.readResponse(akatusResponse);

		final NodeList paymentMethodNodeList = document
				.getElementsByTagName("meio_de_pagamento");

		for (int i = 0, t = paymentMethodNodeList.getLength(); i < t; ++i) {
			final Node paymentMethodNode = paymentMethodNodeList.item(i);

			((PaymentMethodsResponse) akatusResponse)
					.addPaymentMethod(getPaymentMethod(paymentMethodNode));
		}
	}

	private PaymentMethod getPaymentMethod(Node paymentMethodNode) {
		final Element paymentMethodElement = (Element) paymentMethodNode;
		final PaymentMethod paymentMethod = new PaymentMethod();

		paymentMethod.setCode(getNodeTextValue(paymentMethodElement, "codigo"));
		paymentMethod.setDescription(getNodeTextValue(paymentMethodElement,
				"descricao"));
		paymentMethod.setInstallments(Integer.parseInt(getNodeTextValue(
				paymentMethodElement, "parcelas")));

		final NodeList paymentMethodFlagNodeList = paymentMethodElement
				.getElementsByTagName("bandeira");

		for (int i = 0, t = paymentMethodFlagNodeList.getLength(); i < t; ++i) {
			final Element paymentMethodFlagElement = (Element) paymentMethodFlagNodeList
					.item(i);

			paymentMethod.addPaymentMethodFlag(
					getNodeTextValue(paymentMethodFlagElement, "codigo"),
					getNodeTextValue(paymentMethodFlagElement, "descricao"),
					Integer.parseInt(getNodeTextValue(paymentMethodFlagElement,
							"parcelas")));
		}

		return paymentMethod;
	}
}