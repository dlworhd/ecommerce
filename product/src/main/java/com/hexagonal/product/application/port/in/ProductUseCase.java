package com.hexagonal.product.application.port.in;

import com.hexagonal.product.domain.model.CreateProduct;

import java.util.List;

public interface ProductUseCase {

	void createProduct(CreateProduct createProduct);
	void modifyProduct(CreateProduct createProduct, Long productId);
	void deleteProduct(Long productId);
	String getProductsName(List<Long> productIds);
}
