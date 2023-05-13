package com.hexagonal.order.domain;

import com.hexagonal.order.infrastructure.jpa.OrderStatus;
import com.hexagonal.order.infrastructure.jpa.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class OrderDto {


	@AllArgsConstructor
	@Builder
	@Getter
	public static class Request {
		private UUID userId;
		private String name;
		private String addr;
		private String phoneNum;
		private List<ProductInfo> productInfos;
	}

	@AllArgsConstructor
	@Builder
	@Getter
	@Setter
	public static class Response {

		private String paymentUrl;
		private String orderId;
		private String orderName;
		private OrderStatus orderStatus;
		private Long totalQuantity;
		private Long totalAmount;

		public static Response from(OrderEntity orderEntity){
			return Response.builder()
					.orderStatus(orderEntity.getOrderStatus())
					.orderId(orderEntity.getId())
					.totalQuantity(orderEntity.getTotalQuantity())
					.totalAmount(orderEntity.getTotalAmount())
					.build();
		}

	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	public static class ProductInfo{
		private Long id;
		private Long price;
		private Long quantity;
	}
}
