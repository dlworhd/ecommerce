package com.ecommerce.product.application.port.out;

import com.ecommerce.product.domain.model.ProductDto;
import com.ecommerce.product.adapter.out.ProductEntity;

import java.util.List;
import java.util.Map;

public interface ProductPersistencePort {

	ProductDto.Response createProduct(ProductDto.Request request);
	ProductDto.Response modifyProduct(ProductDto.Request request, Long productId);
	ProductDto.Response deleteProduct(Long productId);

	void modifyInventory(Map<Long, Long> productMap);


	List<ProductEntity> getProductEntityByIds(List<Long> productIds);

	List<ProductEntity> getProducts();
}
