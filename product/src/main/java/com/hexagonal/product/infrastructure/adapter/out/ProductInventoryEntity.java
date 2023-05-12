package com.hexagonal.product.infrastructure.adapter.out;

import com.hexagonal.product.domain.model.CreateProduct;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
