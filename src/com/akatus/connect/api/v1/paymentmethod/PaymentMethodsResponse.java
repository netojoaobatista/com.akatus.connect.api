package com.akatus.connect.api.v1.paymentmethod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.akatus.connect.api.AkatusResponse;
import com.akatus.connect.api.v1.entity.PaymentMethod;

public class PaymentMethodsResponse extends AkatusResponse {
	private final List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

	public void addPaymentMethod(PaymentMethod paymentMethod) {
		paymentMethods.add(paymentMethod);
	}

	public Iterator<PaymentMethod> getPaymentMethodIterator() {
		return paymentMethods.iterator();
	}
}