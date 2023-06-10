package com.hexagonal.product.adapter.out;

import com.hexagonal.product.application.port.out.ProductPersistencePort;
import com.hexagonal.product.domain.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		ProductInventoryEntity productInventory = getProductInventory(productId);
		product.update(request);
		productInventory.modifyQuantity(request.getQuantity());

		return ProductDto.Response.from(product);
	}

	@Override
	public void modifyInventory(Map<Long, Long> productMap){
		List<Long> productIds = new ArrayList<>(productMap.keySet());
		List<ProductInventoryEntity> productInventoryEntities = productInventoryEntityRepository.findAllById(productIds);
		productInventoryEntities.stream().forEach(product -> product.setQuantity(productMap.get(product.getProductId())));
	}

	private ProductInventoryEntity getProductInventory(Long productId) {
		return productInventoryEntityRepository.findById(productId).orElseThrow(() -> new RuntimeException("재고 정보를 찾을 수 없습니다."));
	}

	@Override
	public ProductDto.Response deleteProduct(Long productId) {
		ProductEntity product = getProductEntity(productId);
		productRepository.delete(product);
		productInventoryEntityRepository.deleteById(product.getId());
		return ProductDto.Response.from(product);
	}

	@Override
	public List<ProductEntity> getProductEntityByIds(List<Long> productIds) {
		return productRepository.findAllById(productIds);

	}

	@Override
	public List<ProductEntity> getProducts() {
		return productRepository.findAll();
	}

	private ProductEntity getProductEntity(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
	}
}
