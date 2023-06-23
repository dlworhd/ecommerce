package com.ecommerce.order.application.port.out;

import com.ecommerce.order.domain.OrderDto;
import com.ecommerce.order.domain.OrderInfo;
import com.ecommerce.order.adapter.out.OrderStatus;

import java.util.List;

public interface OrderPersistencePort {

	OrderDto.Response createOrder(OrderDto.Request request);
	OrderDto.Response cancelOrder(String orderId);
	List<OrderInfo.Simple> getOrders(OrderDto.Request request);
	void changeStatus(String orderId, OrderStatus orderStatus);
}
