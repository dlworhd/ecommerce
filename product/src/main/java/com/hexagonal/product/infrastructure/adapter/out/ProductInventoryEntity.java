package com.hexagonal.product.infrastructure.adapter.out;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_inventory")
public class ProductInventoryEntity {

	@Id
	private Long productId;
	private Integer quantity;

	public static ProductInventoryEntity from(ProductEntity productEntity, Integer quantity) {
		return ProductInventoryEntity.builder()
				.productId(productEntity.getId())
				.quantity(quantity)
				.build();
	}
}
