package com.navent.challenge.service.impl;

import com.navent.challenge.dao.OrderDAO;
import com.navent.challenge.dto.OrderDTO;
import com.navent.challenge.model.Order;
import com.navent.challenge.model.cache.BumexMemcached;
import com.navent.challenge.service.OrderService;
import com.navent.challenge.util.OrderUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

	private final BumexMemcached memcached = BumexMemcached.getInstance();

	@Override
	public void create(final OrderDTO order) throws IllegalArgumentException {
		if (!Objects.isNull(order.getOrderId())) throw new IllegalArgumentException("El pedido nuevo no puede tener id");
		OrderDAO.insertOrUpdate(OrderUtil.from(order));
	}

	@Override
	public OrderDTO update(final OrderDTO order) throws IllegalArgumentException {
		if (Objects.isNull(order.getOrderId())) throw new IllegalArgumentException("Pedido para actualizar sin id");
		Order orderToUpdate = OrderUtil.from(order);
		synchronized (memcached) {
			OrderDAO.insertOrUpdate(orderToUpdate);
			memcached.set(order.getOrderId(), orderToUpdate);
		}
		return order;
	}

	@Override
	public OrderDTO findById(final Integer id) throws IllegalArgumentException {
		if (Objects.isNull(id)) throw new IllegalArgumentException("Id para buscar pedido invalido");
		Order order = (Order) memcached.get(id.toString());
		if (Objects.isNull(order)) {
			order = OrderDAO.select(id);
			if (!Objects.isNull(order)) {
				memcached.set(id.toString(), order);
			}
		}
		return OrderUtil.from(order);
	}

	@Override
	public void delete(final Integer id) throws IllegalArgumentException {
		if (Objects.isNull(id)) throw new IllegalArgumentException("Pedido para eliminar sin id");
		synchronized (memcached) {
			OrderDAO.delete(new Order(id));
			memcached.delete(id.toString());
		}
	}
}
