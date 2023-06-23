package com.ecommerce.payment.application.port.out;

import com.ecommerce.payment.domain.kakaopay.KakaoPay;
import com.ecommerce.payment.adapter.out.PaymentEntity;

public interface PaymentPersistencePort {

	void createPayment(KakaoPay.ReadyResponse kakaoPay);
	void savePayment(KakaoPay.ApprovalResponse kakaoPay);
	PaymentEntity cancelPayment(Long paymentId);
	PaymentEntity getPaymentEntity(String orderId);




}
