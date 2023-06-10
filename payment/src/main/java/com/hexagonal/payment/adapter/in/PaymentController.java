package com.hexagonal.payment.adapter.in;

import com.hexagonal.payment.domain.kakaopay.KakaoPay;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface PaymentController {

	@PostMapping("/payments")
	ResponseEntity<?> createPayment(@RequestBody KakaoPay.ReadyResponse kakaoPay);

	@PutMapping("/payments/cancel/{paymentId}")
	void cancelPayment(@PathVariable Long paymentId);

	@GetMapping("/payments/approval")
	ResponseEntity<?> approve(
			@RequestParam(value = "pg_token") String pg_token,
			@RequestParam(value = "partner_order_id") String orderId
	);

}