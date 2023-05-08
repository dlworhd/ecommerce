package com.hexagonal.order.infrastructure.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hexagonal.order.application.port.in.OrderUseCase;
import com.hexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

	private final OrderUseCase orderUseCase;

	@Override
	public ResponseEntity<?> createOrder(Order order) throws JsonProcessingException {
		return ResponseEntity.ok(orderUseCase.createOrder(order));
	}

	@Override
	public ResponseEntity<?> cancelOrder(String orderId) {
		orderUseCase.cancelOrder(orderId);
		return ResponseEntity.ok().build();
	}

//	@Override
//	public ResponseEntity<?> getPayUrl(String orderId) {
//		return ResponseEntity.ok(orderUseCase.getPayUrl(orderId));
//	}

	@Override
	public ResponseEntity<?> getOrder(String orderId) {
		 return ResponseEntity.ok(orderUseCase.getOrderInfoById(orderId));
	}
}
