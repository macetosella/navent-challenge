package com.navent.challenge.service;

import com.navent.challenge.dto.OrderDTO;

public interface OrderService {

	void create(final OrderDTO order) throws IllegalArgumentException;

	OrderDTO update(final OrderDTO order) throws IllegalArgumentException;

	OrderDTO findById(final Integer id) throws IllegalArgumentException;

	void delete(final Integer id) throws IllegalArgumentException;

}
