package com.akatus.connect.api.v1.cart;

import com.akatus.connect.api.AkatusResponse;

/**
 * Representação de uma resposta de uma operação do cart.
 * 
 * @author João Batista Neto
 */
public class CartResponse extends AkatusResponse {
	private String cart;
	private String reference;
	private String transaction;
	private String returnURL;

	public String getCart() {
		return cart;
	}

	public String getReference() {
		return reference;
	}

	public String getTransaction() {
		return transaction;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}
}