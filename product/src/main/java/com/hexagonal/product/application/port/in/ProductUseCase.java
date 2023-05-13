package com.hexagonal.product.application.port.in;

import com.hexagonal.product.domain.model.ProductDto;

import java.util.List;

public interface ProductUseCase {

	ProductDto.Response createProduct(ProductDto.Request request);
	ProductDto.Response modifyProduct(ProductDto.Request request, Long productId);
	ProductDto.Response deleteProduct(Long productId);
	String getProductsName(List<Long> productIds);
}
