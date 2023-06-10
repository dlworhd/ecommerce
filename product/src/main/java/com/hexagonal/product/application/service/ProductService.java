package com.hexagonal.product.application.service;

import com.hexagonal.product.application.port.in.ProductUseCase;
import com.hexagonal.product.application.port.out.ProductPersistencePort;
import com.hexagonal.product.domain.model.ProductDto;
import com.hexagonal.product.adapter.out.ProductEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

	private final ProductPersistencePort productPersistencePort;

	@Override
	@Transactional
	public ProductDto.Response createProduct(ProductDto.Request request) {
		return productPersistencePort.createProduct(request);
	}

	@Override
	@Transactional
	public ProductDto.Response modifyProduct(ProductDto.Request request, Long productId) {
		return productPersistencePort.modifyProduct(request, productId);
	}

	@Override
	@Transactional
	public ProductDto.Response deleteProduct(Long productId) {
		return productPersistencePort.deleteProduct(productId);
	}


	@Override
	@Transactional
	public String getProductsName(List<Long> productIds) {
		List<ProductEntity> products = productPersistencePort.getProducts(productIds);
		List<String> productNames = products.stream().map(product -> product.getProductName()).collect(Collectors.toList());

		String customOrderName = "";

		if (productNames != null && productNames.size() > 0) {
			customOrderName = productNames.size() > 1 ?
					productNames.get(0) + " 외 " + (productNames.size() - 1) + "개" :
					productNames.get(0);
		}
		return customOrderName;
	}
}
