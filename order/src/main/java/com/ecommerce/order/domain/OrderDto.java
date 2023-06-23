package com.ecommerce.order.domain;

import com.ecommerce.order.adapter.out.OrderStatus;
import com.ecommerce.order.adapter.out.OrderEntity;
import lombok.*;

import java.util.List;
import java.util.UUID;

public class OrderDto {


	@AllArgsConstructor
	@NoArgsConstructor
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
	@NoArgsConstructor
	public static class ProductInfo{
		private Long id;
		private Long price;
		private Long quantity;
	}
}
