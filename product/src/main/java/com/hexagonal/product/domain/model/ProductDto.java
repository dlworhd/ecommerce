package com.hexagonal.product.domain.model;

import com.hexagonal.product.adapter.out.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@Getter
	public static class Request{
		private String productName;
		private Long price;
		private Long quantity;

	}

	@AllArgsConstructor
	@NoArgsConstructor
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

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	public static class Simple{

		String productImage;
		String productName;
		Long price;

		public static Simple from(ProductEntity product){
			return Simple.builder()
					.productName(product.getProductName())
					.price(product.getPrice())
					.build();
		}
	}
}
