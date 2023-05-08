package com.hexagonal.order.infrastructure.adapter.out;

import com.hexagonal.order.application.port.out.OrderPersistencePort;
import com.hexagonal.order.domain.Order;
import com.hexagonal.order.domain.OrderInfo;
import com.hexagonal.order.infrastructure.jpa.*;
import com.hexagonal.order.infrastructure.jpa.entity.OrderEntity;
import com.hexagonal.order.infrastructure.jpa.entity.OrderItemEntity;
import com.hexagonal.order.infrastructure.jpa.repository.OrderItemRepository;
import com.hexagonal.order.infrastructure.jpa.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderPersistencePort {

	private final OrderGenerator orderGenerator;
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	@Override
	public OrderInfo createOrder(Order order) {
		OrderEntity orderEntity = saverOrder(order);
		addOrderItemInOrder(orderEntity, getOrderItemEntities(order));
		return OrderInfo.from(orderEntity);
	}

	@Transactional
	private OrderEntity saverOrder(Order order) {
		return orderRepository.save(OrderEntity.from(order, orderGenerator));
	}

	@Override
	@Transactional
	public OrderInfo cancelOrder(String orderId) {
		OrderEntity order = getOrderById(orderId);
		order.setOrderStatus(OrderStatus.CANCELED);
		return OrderInfo.from(order);
	}

	@Override
	public OrderInfo getOrder(String orderId) {
		return OrderInfo.from(getOrderById(orderId));
	}

	private void addOrderItemInOrder(OrderEntity orderEntity, List<OrderItemEntity> orderItemEntities) {
		orderItemEntities.stream()
				.forEach(orderItemEntity -> orderEntity.addOrderItemEntities(orderItemEntity));
	}

	private List<OrderItemEntity> getOrderItemEntities(Order order) {
		return orderItemRepository.saveAll(getOrderItemEntitiesFromInfos(order.getProductInfos()));
	}

	private List<OrderItemEntity> getOrderItemEntitiesFromInfos(List<Order.ProductInfo> productInfoList) {
		return productInfoList.stream()
				.map(productInfo -> OrderItemEntity.from(productInfo))
				.collect(Collectors.toList());
	}

	private OrderEntity getOrderById(String orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found!"));
	}
}
