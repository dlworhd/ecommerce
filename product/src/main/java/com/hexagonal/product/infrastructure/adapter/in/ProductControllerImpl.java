package com.hexagonal.product.infrastructure.adapter.in;

import com.hexagonal.product.application.port.in.ProductUseCase;
import com.hexagonal.product.application.service.ProductService;
import com.hexagonal.product.domain.model.CreateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController{

	private final ProductUseCase productUseCase;
	private final KafkaTemplate<String, CreateProduct> kafkaTemplate;
	private final String TOPIC = "product-topic";

	public void sendMessage(CreateProduct createProduct){
		kafkaTemplate.send(TOPIC, createProduct);
	}

	@Override
	public ResponseEntity<Void> createProduct(@RequestBody CreateProduct createProduct) {
		productUseCase.createProduct(createProduct);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> modifyProduct(@RequestBody CreateProduct createProduct, Long productId) {
		productUseCase.modifyProduct(createProduct, productId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> deleteProduct(@RequestBody Long productId) {
		productUseCase.deleteProduct(productId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> getProductsName(List<Long> productIds) {
		return ResponseEntity.ok(productUseCase.getProductsName(productIds));
	}
}
