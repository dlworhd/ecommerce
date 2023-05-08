package com.hexagonal.product.application.port.in;

import com.hexagonal.product.domain.model.CreateProduct;

public interface ProductUseCase {

	void createProduct(CreateProduct createProduct);
	void modifyProduct(CreateProduct createProduct, Long productId);
	void deleteProduct(Long productId);
}
