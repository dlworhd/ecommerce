package com.ecommerce.order.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ecommerce.order.domain.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface OrderController {

	@PostMapping("/orders/create")
	ResponseEntity<?> createOrder(@RequestBody OrderDto.Request request) throws JsonProcessingException;

	@PutMapping("/orders/cancel/{orderId}")
	ResponseEntity<?> cancelOrder(@PathVariable String orderId);

	@GetMapping("/orders")
	ResponseEntity<?> orderList(@RequestBody OrderDto.Request request);
}
