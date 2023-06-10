package com.hexagonal.order.application.port.out;

import com.hexagonal.order.domain.OrderDto;
import com.hexagonal.order.domain.OrderInfo;
import com.hexagonal.order.adapter.out.OrderStatus;

import java.util.List;

public interface OrderPersistencePort {

	OrderDto.Response createOrder(OrderDto.Request request);
	OrderDto.Response cancelOrder(String orderId);
	List<OrderInfo.Simple> getOrders(OrderDto.Request request);
	void changeStatus(String orderId, OrderStatus orderStatus);
}
