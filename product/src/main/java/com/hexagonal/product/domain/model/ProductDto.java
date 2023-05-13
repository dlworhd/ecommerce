package com.hexagonal.product.domain.model;

import com.hexagonal.product.infrastructure.adapter.out.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ProductDto {

	@AllArgsConstructor
	@Builder
	@Getter
	public static class Request{
		private String productName;
		private Long price;
		private Integer quantity;

	}

	@AllArgsConstructor
	@Builder
	@Getter
	public static class Response{
		private Long productId;

		public static Response from(ProductEntity product){
			return ProductDto.Response
					.builder()
					.productId(product.getId())
					.build();
		}
	}
}
