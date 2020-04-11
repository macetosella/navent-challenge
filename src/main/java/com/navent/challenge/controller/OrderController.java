package com.navent.challenge.controller;

import com.navent.challenge.dto.OrderDTO;
import com.navent.challenge.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(value = "/pedido")
	public String index() {
		return "index";
	}

	@PostMapping(value = "/pedido/guardar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void guardar(@RequestBody OrderDTO order) {
		orderService.create(order);
	}
}


