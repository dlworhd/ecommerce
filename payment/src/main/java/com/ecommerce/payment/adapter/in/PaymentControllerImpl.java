package com.ecommerce.payment.adapter.in;

import com.ecommerce.payment.application.port.in.PaymentUseCase;
import com.ecommerce.payment.domain.kakaopay.KakaoPay;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentControllerImpl implements PaymentController {

	private final PaymentUseCase paymentUseCase;

	@Override
	public ResponseEntity<?> createPayment(KakaoPay.ReadyResponse kakaoPay) {
		paymentUseCase.createPayment(kakaoPay);
		return ResponseEntity.ok().build();
	}
	@Override
	public void cancelPayment(Long paymentId) {
		paymentUseCase.cancelPayment(paymentId);
	}

	@Override
	public ResponseEntity<?> approve(String pg_token, String orderId) {
		paymentUseCase.approvePayment(pg_token, orderId);
		return null;
	}
}
