package com.hexagonal.order.infrastructure.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hexagonal.order.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface OrderController {

	@PostMapping("/orders/create")
	ResponseEntity<?> createOrder(@RequestBody Order order) throws JsonProcessingException;

	@PutMapping("/orders/cancel/{orderId}")
	ResponseEntity<?> cancelOrder(@PathVariable String orderId);

//	@GetMapping("/orders/{orderId}/pay-url")
//	ResponseEntity<?> getPayUrl(@PathVariable String orderId);

	@GetMapping("/orders/{orderId}")
	ResponseEntity<?> getOrder(@PathVariable String orderId);
}
