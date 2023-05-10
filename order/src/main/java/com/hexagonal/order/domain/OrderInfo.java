package com.hexagonal.order.domain;

import com.hexagonal.order.infrastructure.jpa.OrderStatus;
import com.hexagonal.order.infrastructure.jpa.entity.OrderEntity;
import com.hexagonal.order.infrastructure.jpa.entity.OrderItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class OrderInfo {

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Simple {
		private String orderId;
		private Long totalAmount;
		private OrderStatus orderStatus;
		private LocalDate createdAt;

		public static Simple from(OrderEntity order){

			return Simple.builder()
					.orderId(order.getId())
					.orderStatus(order.getOrderStatus())
					.totalAmount(order.getOrderItemEntities().stream().mapToLong(item -> item.getTotalAmount()).sum())
					.createdAt(order.getCreatedAt())
					.build();
		}
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Detail {
		private String id;
		private String name;
		private String addr;
		private String phoneNum;
		private OrderStatus orderStatus;
		private List<Order.ProductInfo> productInfos;


	}
}
