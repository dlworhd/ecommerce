package com.hexagonal.product.adapter.out;


import com.hexagonal.product.domain.model.ProductDto;
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
	private Long price;

	private String productName;
	private LocalDate createdAt;


	public static ProductEntity from(ProductDto.Request request) {
		return ProductEntity.builder()
				.productName(request.getProductName())
				.price(request.getPrice())
				.createdAt(LocalDate.now())
				.build();
	}

	public void update(ProductDto.Request request) {
		this.productName = request.getProductName();
		this.price = request.getPrice();
	}
}
