package com.hexagonal.order.application.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hexagonal.order.domain.Order;

public interface OrderUseCase {
	//redirect 반환
	String createOrder(Order order) throws JsonProcessingException;
	void cancelOrder(String orderId);
}
