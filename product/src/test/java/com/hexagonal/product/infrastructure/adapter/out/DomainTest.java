package com.hexagonal.product.infrastructure.adapter.out;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DomainTest {



	@Test
	@DisplayName("재고 증가 성공")
	void increaseSuccess(){

		ProductEntity product = ProductEntity.builder()
				.id(1L)
				.build();

		ProductInventoryEntity inventory = new ProductInventoryEntity(product.getId(), 100);
		inventory.increaseQuantity(10);

		assertThat(inventory.getQuantity()).isEqualTo(110);
	}

	@Test
	@DisplayName("재고 감소 성공")
	void decreaseSuccess(){

		ProductEntity product = ProductEntity.builder()
				.id(1L)
				.build();

		ProductInventoryEntity inventory = new ProductInventoryEntity(product.getId(), 100);
		inventory.decreaseQuantity(10);

		assertThat(inventory.getQuantity()).isEqualTo(90);
	}

	@Test
	@DisplayName("재고 감소 실패")
	void decreaseFail(){

		ProductEntity product = ProductEntity.builder()
				.id(1L)
				.build();

		ProductInventoryEntity inventory = new ProductInventoryEntity(product.getId(), 10);
		Assertions.assertThrows(RuntimeException.class,() -> inventory.decreaseQuantity(11));
	}
}