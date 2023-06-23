package com.ecommerce.order.application.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ecommerce.order.domain.OrderDto;
import com.ecommerce.order.domain.OrderInfo;

import java.util.List;

public interface OrderUseCase {

	OrderDto.Response createOrder(OrderDto.Request request) throws JsonProcessingException;
	OrderDto.Response cancelOrder(String orderId);

	List<OrderInfo.Simple> getOrders(OrderDto.Request request);
}
