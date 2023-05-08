package com.hexagonal.product.infrastructure.adapter.in;

import com.hexagonal.product.domain.model.CreateProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ProductController {

	@PostMapping("/products")
	ResponseEntity<Void> createProduct(@RequestBody CreateProduct createProduct);

	@PutMapping("/products/{productId}")
	ResponseEntity<Void> modifyProduct(@RequestBody CreateProduct createProduct,
	                                   @PathVariable Long productId);

	@DeleteMapping("/products/{productId}")
	ResponseEntity<Void> deleteProduct(@PathVariable Long productId);

}
