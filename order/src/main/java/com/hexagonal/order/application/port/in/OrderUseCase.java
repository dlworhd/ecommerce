package com.hexagonal.order.application.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hexagonal.order.domain.Order;
import com.hexagonal.order.domain.OrderInfo;

import java.util.List;

public interface OrderUseCase {
	//redirect 반환
	String createOrder(Order order) throws JsonProcessingException;
	void cancelOrder(String orderId);

	List<OrderInfo.Simple> getOrders(Order order);
}
