package com.hexagonal.product.infrastructure.adapter.out;

import com.hexagonal.product.application.port.out.ProductPersistencePort;
import com.hexagonal.product.domain.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

	private final ProductRepository productRepository;
	private final ProductInventoryEntityRepository productInventoryEntityRepository;

	@Override
	public ProductDto.Response createProduct(ProductDto.Request request) {
		ProductEntity product = productRepository.save(ProductEntity.from(request));
		productInventoryEntityRepository.save(ProductInventoryEntity.from(product, request.getQuantity()));

		return ProductDto.Response.from(product);
	}

	@Override
	public ProductDto.Response modifyProduct(ProductDto.Request request, Long productId) {
		ProductEntity product = getProductEntity(productId);
		product.copy(request);
		return ProductDto.Response.from(product);
	}

	@Override
	public ProductDto.Response deleteProduct(Long productId) {
		ProductEntity product = getProductEntity(productId);
		productRepository.delete(product);
		productInventoryEntityRepository.deleteById(product.getId());
		return ProductDto.Response.from(product);
	}

	@Override
	public List<ProductEntity> getProducts(List<Long> productIds) {
		return productRepository.findAllById(productIds);

	}

	private ProductEntity getProductEntity(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
	}
}
