package com.hexagonal.payment.application.port.out;

import com.hexagonal.payment.domain.kakaopay.KakaoPay;
import com.hexagonal.payment.infrastructure.jpa.entity.PaymentEntity;

public interface PaymentPersistencePort {

	void createPayment(KakaoPay.ReadyResponse kakaoPay);
	void savePayment(KakaoPay.ApprovalResponse kakaoPay);
	PaymentEntity cancelPayment(Long paymentId);
	PaymentEntity getPaymentEntity(String orderId);




}
