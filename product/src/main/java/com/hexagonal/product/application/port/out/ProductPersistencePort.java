package com.hexagonal.product.application.port.out;

import com.hexagonal.product.domain.model.ProductDto;
import com.hexagonal.product.infrastructure.adapter.out.ProductEntity;

import java.util.List;

public interface ProductPersistencePort {

	ProductDto.Response createProduct(ProductDto.Request request);
	ProductDto.Response modifyProduct(ProductDto.Request request, Long productId);
	ProductDto.Response deleteProduct(Long productId);

	List<ProductEntity> getProducts(List<Long> productIds);
}
