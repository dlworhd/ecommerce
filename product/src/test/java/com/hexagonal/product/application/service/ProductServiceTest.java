package com.hexagonal.product.application.service;

import com.hexagonal.product.application.port.out.ProductPersistencePort;
import com.hexagonal.product.domain.model.ProductDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	ProductPersistencePort productPersistencePort;

	@InjectMocks
	ProductService productService;

	@Test
	@DisplayName("상품 등록")
	void createProduct() {
		ProductDto.Request request = ProductDto.Request.builder()
				.productName("nike")
				.price(1000L)
				.quantity(10)
				.build();

		when(productService.createProduct(request)).thenReturn(ProductDto.Response.builder().productId(1L).build());

		ProductDto.Response product = productService.createProduct(request);
		Assertions.assertThat(product.getProductId()).isEqualTo(1L);

	}

	@Test
	@DisplayName("상품 수정")
	void modifyProduct() {

		ProductDto.Request request = ProductDto.Request.builder()
				.productName("nike")
				.price(1000L)
				.quantity(10)
				.build();

		when(productService.modifyProduct(request, 1L)).thenReturn(ProductDto.Response.builder().productId(1L).build());

		ProductDto.Response product = productService.modifyProduct(request, 1L);
		Assertions.assertThat(product.getProductId()).isEqualTo(1L);

	}

	@Test
	@DisplayName("상품 삭제")
	void deleteProduct() {

		when(productService.deleteProduct(1L)).thenReturn(ProductDto.Response.builder().productId(1L).build());

		ProductDto.Response product = productService.deleteProduct(1L);
		Assertions.assertThat(product.getProductId()).isEqualTo(1L);

	}

}
