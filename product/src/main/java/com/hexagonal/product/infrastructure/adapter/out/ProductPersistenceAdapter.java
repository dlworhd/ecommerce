package com.hexagonal.product.infrastructure.adapter.out;

import com.hexagonal.product.domain.model.CreateProduct;
import com.hexagonal.product.application.port.out.ProductPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

	private final ProductRepository productRepository;
	private final ProductInventoryEntityRepository productInventoryEntityRepository;

	@Override
	public void createProduct(CreateProduct createProduct) {
		ProductEntity product = productRepository.save(ProductEntity.from(createProduct));
		productInventoryEntityRepository.save(ProductInventoryEntity.from(product, createProduct.getQuantity()));
	}

	@Override
	public void modifyProduct(CreateProduct createProduct, Long productId) {
		ProductEntity productEntity = getProductEntity(productId);
		productEntity.copy(createProduct);
	}

	@Override
	public void deleteProduct(Long productId) {
		ProductEntity productEntity = getProductEntity(productId);
		productRepository.delete(productEntity);
		productInventoryEntityRepository.deleteById(productEntity.getId());
	}

	@Override
	public List<ProductEntity> getProducts(List<Long> productIds) {
		return productRepository.findAllById(productIds);

	}

	private ProductEntity getProductEntity(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
	}
}
