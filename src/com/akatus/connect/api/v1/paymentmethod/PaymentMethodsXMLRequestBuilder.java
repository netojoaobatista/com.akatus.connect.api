package com.akatus.connect.api.v1.paymentmethod;

import org.w3c.dom.Element;

import com.akatus.connect.api.AkatusOperation;
import com.akatus.connect.api.v1.AkatusXMLRequestBuilder;

class PaymentMethodsXMLRequestBuilder extends AkatusXMLRequestBuilder {
	public String build(AkatusOperation operation) {
		createDocument();
		createPaymentMethods((PaymentMethodsOperation) operation);

		return transformDocument();
	}

	private void createPaymentMethods(PaymentMethodsOperation operation) {
		final Element paymentMethodsElement = document
				.createElement("meios_de_pagamento");

		paymentMethodsElement.appendChild(createAccountElement("correntista",
				operation));

		document.appendChild(paymentMethodsElement);
	}
}