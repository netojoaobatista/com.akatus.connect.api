package com.akatus.connect.api.v1.entity;

public class Phone implements Entity {
	public enum Type {
		COMMERCIAL("comercial"), RESIDENTIAL("residencial"), CELLPHONE(
				"celular");

		private final String type;

		Type(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return type;
		}
	}

	private String number;
	private Type type;

	public String getNumber() {
		return number;
	}

	public Type getType() {
		return type;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setType(Type type) {
		this.type = type;
	}
}