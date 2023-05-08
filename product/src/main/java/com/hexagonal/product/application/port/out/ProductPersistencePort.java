package com.hexagonal.product.application.port.out;

import com.hexagonal.product.domain.model.CreateProduct;
import com.hexagonal.product.infrastructure.adapter.out.ProductEntity;

import java.util.List;

public interface ProductPersistencePort {

	void createProduct(CreateProduct createProduct);
	void modifyProduct(CreateProduct createProduct, Long productId);
	void deleteProduct(Long productId);

	List<ProductEntity> getProducts(List<Long> productIds);
}
