package com.akatus.connect.api.v1.entity;

public class AkatusAccount implements Entity {
	private String apiKey;
	private String email;

	public AkatusAccount() {
	}

	public AkatusAccount(String apiKey, String email) {
		setApiKey(apiKey);
		setEmail(email);
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getEmail() {
		return email;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}