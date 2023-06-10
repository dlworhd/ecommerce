package com.hexagonal.product.adapter.in;

import com.hexagonal.product.domain.model.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductController {

	@PostMapping("/products")
	ResponseEntity<?> createProduct(@RequestBody ProductDto.Request request);

	@PutMapping("/products/{productId}")
	ResponseEntity<?> modifyProduct(@RequestBody ProductDto.Request request,
	                                   @PathVariable Long productId);

	@DeleteMapping("/products/{productId}")
	ResponseEntity<?> deleteProduct(@PathVariable Long productId);

	@PostMapping("/products/name")
	ResponseEntity<?> getProductsName(@RequestBody List<Long> productIds);
}
