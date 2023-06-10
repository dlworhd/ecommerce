package com.hexagonal.order.adapter.out;

import com.hexagonal.order.application.port.out.OrderPersistencePort;
import com.hexagonal.order.domain.OrderDto;
import com.hexagonal.order.domain.OrderInfo;
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
	public OrderDto.Response createOrder(OrderDto.Request request) {
		OrderEntity orderEntity = saverOrder(request);
		addOrderItemInOrder(orderEntity, saveOrderItemEntities(request));
		return OrderDto.Response.from(orderEntity);
	}

	private OrderEntity saverOrder(OrderDto.Request request) {
		return orderRepository.save(OrderEntity.from(request, orderGenerator));
	}

	@Override
	public OrderDto.Response cancelOrder(String orderId) {
		OrderEntity orderEntity = getOrderById(orderId);
		orderEntity.setOrderStatus(OrderStatus.CANCELLED);
		return OrderDto.Response.builder()
				.orderId(orderEntity.getId())
				.build();
	}

	public List<OrderInfo.Simple> getOrders(OrderDto.Request request) {
		List<OrderEntity> orderEntities = orderRepository.findOrderWithOrderItemsById(request.getUserId());
		return orderEntities.stream().map(orderEntity -> OrderInfo.Simple.from(orderEntity)).collect(Collectors.toList());
	}

	@Override
	public void changeStatus(String orderId, OrderStatus orderStatus) {
		OrderEntity orderEntity = getOrder(orderId);
		orderEntity.changeStatus(orderStatus);
	}


	public OrderEntity getOrder(String orderId) {
		return getOrderById(orderId);
	}

	private void addOrderItemInOrder(OrderEntity orderEntity, List<OrderItemEntity> orderItemEntities) {
		orderItemEntities.stream()
				.forEach(orderItemEntity -> orderEntity.addOrderItemEntities(orderItemEntity));
	}

	private List<OrderItemEntity> saveOrderItemEntities(OrderDto.Request request) {
		return orderItemRepository.saveAll(getOrderItemEntitiesFromInfos(request.getProductInfos()));
	}

	private List<OrderItemEntity> getOrderItemEntitiesFromInfos(List<OrderDto.ProductInfo> productInfoList) {
		return productInfoList.stream()
				.map(productInfo -> OrderItemEntity.from(productInfo))
				.collect(Collectors.toList());
	}

	private OrderEntity getOrderById(String orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found!"));
	}
}
