package com.ecommerce.order.domain;

import com.ecommerce.order.adapter.out.OrderStatus;
import com.ecommerce.order.adapter.out.OrderEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


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
		private List<OrderDto.ProductInfo> productInfos;


	}
}
