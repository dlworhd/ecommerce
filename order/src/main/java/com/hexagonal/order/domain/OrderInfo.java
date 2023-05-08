package com.hexagonal.order.domain;

import com.hexagonal.order.infrastructure.jpa.OrderStatus;
import com.hexagonal.order.infrastructure.jpa.entity.OrderEntity;
import com.hexagonal.order.infrastructure.jpa.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderInfo {
	private String orderId;
	private String orderName;
	private Long totalAmount;
	private Long totalQuantity;
	private OrderStatus orderStatus;

	public static OrderInfo from(OrderEntity orderEntity) {
		List<OrderItemEntity> orderItemEntities = orderEntity.getOrderItemEntities();

		String customOrderName = createOrderName(orderItemEntities);

		return OrderInfo.builder()
				.orderId(orderEntity.getId())
				.orderName(customOrderName)
				.orderStatus(orderEntity.getOrderStatus())
				.totalAmount(orderItemEntities.stream()
						.mapToLong(orderItemEntity -> orderItemEntity.getTotalAmount())
						.sum()
				)
				.totalQuantity(orderItemEntities.stream()
						.mapToLong(orderItemEntity -> orderItemEntity.getTotalQuantity())
						.sum())
				.build();
	}

	private static String createOrderName(List<OrderItemEntity> orderItemEntities) {
		String customOrderName = "";

		if (orderItemEntities != null && orderItemEntities.size() > 0) {
			customOrderName = orderItemEntities.size() > 1 ?
					orderItemEntities.get(0).getProductName() + "외 " + (orderItemEntities.size() - 1) + "개" :
					orderItemEntities.get(0).getProductName();
		}
		return customOrderName;
	}
}
