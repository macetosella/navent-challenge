package com.navent.challenge.util;

import com.navent.challenge.dto.OrderDTO;
import com.navent.challenge.model.Order;

public class OrderUtil {
	public static OrderDTO from(Order order) {
		return new OrderDTO(order.getOrderId().toString(), order.getName(), order.getAmount(), order.getDiscount());
	}
	public static Order from(OrderDTO orderDTO){
		return new Order(Integer.getInteger(orderDTO.getName()), orderDTO.getName(), orderDTO.getAmount(), orderDTO.getDiscount());
	}
}
