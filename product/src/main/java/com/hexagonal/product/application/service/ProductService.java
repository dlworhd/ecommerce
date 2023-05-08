package com.hexagonal.product.application.service;

import com.hexagonal.product.application.port.in.ProductUseCase;
import com.hexagonal.product.domain.model.CreateProduct;
import com.hexagonal.product.application.port.out.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

	private final ProductPersistencePort productPersistencePort;

	@Override
	public void createProduct(CreateProduct createProduct) {
		productPersistencePort.createProduct(createProduct);
	}

	@Override
	public void modifyProduct(CreateProduct createProduct, Long productId) {
		productPersistencePort.modifyProduct(createProduct, productId);
	}

	@Override
	public void deleteProduct(Long productId) {
		productPersistencePort.deleteProduct(productId);
	}
}
