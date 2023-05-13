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

	public void increaseQuantity(Integer quantity){
		Integer existingQuantity = this.quantity;
		this.quantity = existingQuantity + quantity;
	}

	public void decreaseQuantity(Integer quantity){
		Integer existingQuantity = this.quantity;
		if(existingQuantity < quantity) throw new RuntimeException("설정하신 양보다 재고가 더 적습니다.");
		this.quantity = existingQuantity - quantity;
	}
}
