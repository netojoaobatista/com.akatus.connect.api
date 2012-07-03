package com.akatus.connect.api.v1.entity;

public class Product implements Entity {
	private String code;
	private String description;
	private Double discount = 0.0;
	private Double price = 0.0;
	private Integer quantity = 1;
	private Double shipping = 0.0;
	private Double weight = 0.0;

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Double getDiscount() {
		return discount;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getShipping() {
		return shipping;
	}

	public Double getWeight() {
		return weight;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setShipping(Double shipping) {
		this.shipping = shipping;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}