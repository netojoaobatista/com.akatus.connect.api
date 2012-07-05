package com.akatus.connect.api.v1.paymentmethod;

import com.akatus.connect.api.Akatus;
import com.akatus.connect.api.AkatusOperation;
import com.akatus.connect.api.AkatusRequestBuilder;
import com.akatus.connect.api.AkatusResponseBuilder;

/**
 * Implementação da operação meios de pagamento da API da Akatus.
 * 
 * @author João Batista Neto
 */
public class PaymentMethodsOperation extends AkatusOperation {
	public PaymentMethodsOperation(Akatus akatus) {
		super(akatus);
	}

	protected AkatusRequestBuilder createDefaultRequestBuilder() {
		return new PaymentMethodsXMLRequestBuilder();
	}

	protected AkatusResponseBuilder createDefaultResponseBuilder() {
		return new PaymentMethodsXMLResponseBuilder();
	}

	public String getPath() {
		final StringBuilder pathBuilder = new StringBuilder();

		pathBuilder.append("/api/");
		pathBuilder.append(getVersion());
		pathBuilder.append("/meios-de-pagamento.xml");

		return pathBuilder.toString();
	}
}