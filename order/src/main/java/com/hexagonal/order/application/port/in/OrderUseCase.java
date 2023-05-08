package com.hexagonal.order.application.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hexagonal.order.domain.Order;
import com.hexagonal.order.domain.OrderInfo;

public interface OrderUseCase {


	//redirect 반환
	String createOrder(Order order) throws JsonProcessingException;
	OrderInfo cancelOrder(String orderId);
	OrderInfo getOrderInfoById(String orderId);
}
