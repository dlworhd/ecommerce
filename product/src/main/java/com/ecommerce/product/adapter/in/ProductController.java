package com.ecommerce.product.adapter.in;

import com.ecommerce.product.domain.model.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductController {


	@GetMapping("/products")
	ResponseEntity<?> getProducts();

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
