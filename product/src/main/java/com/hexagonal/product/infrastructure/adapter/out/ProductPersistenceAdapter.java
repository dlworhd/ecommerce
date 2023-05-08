package com.hexagonal.product.infrastructure.adapter.out;

import com.hexagonal.product.domain.model.CreateProduct;
import com.hexagonal.product.application.port.out.ProductPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

	private final ProductRepository productRepository;

	@Override
	@Transactional
	public void createProduct(CreateProduct createProduct) {
		productRepository.save(ProductEntity.from(createProduct));
	}

	@Override
	@Transactional
	public void modifyProduct(CreateProduct createProduct, Long productId) {
		ProductEntity productEntity = getProductEntity(productId);
		productEntity.copy(createProduct);
	}

	@Override
	@Transactional
	public void deleteProduct(Long productId) {
		ProductEntity productEntity = getProductEntity(productId);
		productRepository.delete(productEntity);
	}

	private ProductEntity getProductEntity(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
	}
}
