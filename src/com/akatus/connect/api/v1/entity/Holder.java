package com.akatus.connect.api.v1.entity;

public class Holder implements Entity {
	private String document;
	private String name;
	private String phone;

	public String getDocument() {
		return document;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}