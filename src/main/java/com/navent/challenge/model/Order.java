package com.navent.challenge.model;

public class Order{

	private Integer orderId;

	private String name;

	private Float amount;

	private Float discount;

	public Order(Integer orderId) {
		this.orderId = orderId;
	}

	public Order(Integer orderId, String name, Float amount, Float discount) {
		this.orderId = orderId;
		this.name = name;
		this.amount = amount;
		this.discount = discount;
	}


	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}
}