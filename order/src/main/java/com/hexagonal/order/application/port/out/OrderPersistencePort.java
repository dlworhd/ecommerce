package com.hexagonal.order.application.port.out;

import com.hexagonal.order.domain.Order;
import com.hexagonal.order.domain.OrderInfo;
import com.hexagonal.order.infrastructure.jpa.entity.OrderEntity;

import java.util.List;

public interface OrderPersistencePort {

	OrderEntity getOrder(String orderId);
	OrderEntity createOrder(Order order);
	void cancelOrder(String orderId);

	List<OrderInfo.Simple> getOrders(Order order);
}
