package com.hexagonal.product.application.port.out;

import com.hexagonal.product.domain.model.CreateProduct;

public interface ProductPersistencePort {

	void createProduct(CreateProduct createProduct);
	void modifyProduct(CreateProduct createProduct, Long productId);
	void deleteProduct(Long productId);

}
