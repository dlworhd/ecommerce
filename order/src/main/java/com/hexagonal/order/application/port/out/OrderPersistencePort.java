package com.hexagonal.order.application.port.out;

import com.hexagonal.order.domain.Order;
import com.hexagonal.order.domain.OrderInfo;

import java.util.Map;

public interface OrderPersistencePort {

	OrderInfo getOrder(String orderId);
	OrderInfo createOrder(Order order);
	OrderInfo cancelOrder(String orderId);
}
