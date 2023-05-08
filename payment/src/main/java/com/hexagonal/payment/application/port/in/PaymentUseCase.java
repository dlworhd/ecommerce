package com.hexagonal.payment.application.port.in;

import com.hexagonal.payment.domain.kakaopay.KakaoPay;

public interface PaymentUseCase {

	void createPayment(KakaoPay.ReadyResponse kakaoPay);
	void cancelPayment(Long paymentId);
	void approvePayment(String pgToken, String orderId);
}
