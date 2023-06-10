package com.hexagonal.product.adapter.out;

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
	private Long quantity;

	public static ProductInventoryEntity from(ProductEntity productEntity, Long quantity) {
		return ProductInventoryEntity.builder()
				.productId(productEntity.getId())
				.quantity(quantity)
				.build();
	}

	public void increaseQuantity(Long changeQuantity) {
		Long nowQuantity = getQuantity();
		this.quantity = nowQuantity + changeQuantity;
	}

	public void decreaseQuantity(Long changeQuantity) {
		Long nowQuantity = getQuantity();
		if (nowQuantity < changeQuantity) throw new RuntimeException("설정하신 양보다 재고가 더 적습니다.");
		this.quantity = nowQuantity - changeQuantity;
	}

	public void modifyQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
