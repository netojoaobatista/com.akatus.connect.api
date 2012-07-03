package com.akatus.connect.api.v1.entity;

/**
 * Representação de um endereço do cliente
 * 
 * @author João Batista Neto
 */
public class Address implements Entity {
	/**
	 * Tipos de endereços
	 */
	public enum Type {
		COMMERCIAL("commercial"), SHIPPING("entrega");

		private final String type;

		Type(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return type;
		}
	}

	/**
	 * País do cliente
	 */
	public enum Country {
		BRA("BRA");

		private final String country;

		Country(String country) {
			this.country = country;
		}

		@Override
		public String toString() {
			return country;
		}
	}

	/**
	 * Unidades Federativas do Brasil. Esses são os estados válidos para o
	 * endereço do cliente.
	 */
	public enum State {
		AC("AC"), AL("AL"), AM("AM"), BA("BA"), CE("CE"), ES("ES"), DF("DF"), GO(
				"GO"), MA("MA"), MT("MT"), MS("MS"), MG("MG"), PA("PA"), PB(
				"PB"), PR("PR"), PE("PE"), PI("PI"), RJ("RJ"), RN("RN"), RS(
				"RS"), RO("RO"), RR("RR"), SC("SC"), SP("SP"), SE("SE"), TO(
				"TO");

		private final String state;

		State(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return state;
		}
	}

	private String city;
	private String complement;
	private Country country = Country.BRA;
	private String neighbourhood;
	private Integer number;
	private State state;
	private String street;
	private Type type;
	private String zip;

	public String getCity() {
		return city;
	}

	public String getComplement() {
		return complement;
	}

	public Country getCountry() {
		return country;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public Integer getNumber() {
		return number;
	}

	public State getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public Type getType() {
		return type;
	}

	public String getZip() {
		return zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}