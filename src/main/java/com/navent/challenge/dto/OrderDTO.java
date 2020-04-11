package com.navent.challenge.dto;

import java.io.Serializable;
import java.util.Objects;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = -2357866120705174050L;

	private String orderId;
	private String name;
	private Float amount;
	private Float discount;

	public OrderDTO() {
	}

	public OrderDTO(String name, Float amount, Float discount) {
		this.name = name;
		this.amount = amount;
		this.discount = discount;
	}

	public OrderDTO(String orderId, String name, Float amount, Float discount) {
		this.orderId = orderId;
		this.name = name;
		this.amount = amount;
		this.discount = discount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderDTO)) return false;
		OrderDTO orderDTO = (OrderDTO) o;
		return getOrderId().equals(orderDTO.getOrderId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getOrderId(), getName(), getAmount(), getDiscount());
	}

	@Override
	public String toString() {
		return "OrderDTO{" +
				"orderId='" + orderId + '\'' +
				", name='" + name + '\'' +
				", amount=" + amount +
				", discount=" + discount +
				'}';
	}
}
