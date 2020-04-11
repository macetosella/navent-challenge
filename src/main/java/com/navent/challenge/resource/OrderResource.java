package com.navent.challenge.resource;

import com.navent.challenge.dto.OrderDTO;
import com.navent.challenge.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class OrderResource {

	private static final Logger LOGGER = LogManager.getLogger(OrderResource.class);
	private OrderService orderService;

	@Autowired
	public OrderResource(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newOrder(@RequestBody OrderDTO newOrder) throws IllegalArgumentException {
		LOGGER.info("Request to CREATE a new order");
		try {
			orderService.create(newOrder);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error creating a new order", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(path = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getOrder(@PathVariable Integer id) throws IllegalArgumentException {
		LOGGER.info("Request to SELECT order: " + id);
		OrderDTO orderDTO;
		try {
			orderDTO = orderService.findById(id);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error getting order", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().body(orderDTO);
	}

	@PutMapping(path = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateOrder(@RequestBody OrderDTO updatedOrder) throws IllegalArgumentException {
		LOGGER.info("Request to UPDATE order: " + updatedOrder);
		OrderDTO orderDTO;
		try {
			orderDTO = orderService.update(updatedOrder);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error updating order", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().body(orderDTO);
	}

	@DeleteMapping(path = "/orders/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteOrder(@PathVariable Integer id) throws IllegalArgumentException {
		LOGGER.info("Request to DELETE order: " + id);
		try {
			orderService.delete(id);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error removing order", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}
}
