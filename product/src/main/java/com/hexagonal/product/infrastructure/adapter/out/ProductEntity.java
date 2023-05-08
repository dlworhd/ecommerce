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
@Table(name = "product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productName;
	private Long price;
	private LocalDate createdAt;


	public static ProductEntity from(CreateProduct createProduct) {
		return ProductEntity.builder()
				.productName(createProduct.getProductName())
				.price(createProduct.getPrice())
				.createdAt(LocalDate.now())
				.build();
	}

	public void copy(CreateProduct createProduct) {
		this.productName = createProduct.getProductName();
		this.price = createProduct.getPrice();
	}
}
