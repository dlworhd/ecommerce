package com.ecommerce.order.adapter.out;

import com.ecommerce.order.domain.OrderDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "order_item")
public class OrderItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private OrderEntity order;

	private Long productId;
	private Long totalQuantity;
	private Long totalAmount;

	public static OrderItemEntity from(OrderDto.ProductInfo productInfo) {

		return OrderItemEntity.builder()
				.totalAmount(productInfo.getPrice() * productInfo.getQuantity())
				.totalQuantity(productInfo.getQuantity())
				.productId(productInfo.getId())
				.build();
	}
}
