package com.ecommerce.payment.application.port.in;

import com.ecommerce.payment.domain.kakaopay.KakaoPay;

public interface PaymentUseCase {

	void createPayment(KakaoPay.ReadyResponse kakaoPay);
	void cancelPayment(Long paymentId);
	void approvePayment(String pgToken, String orderId);
}
