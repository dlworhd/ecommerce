package com.hexagonal.payment.infrastructure.adapter.out;

import com.hexagonal.payment.application.port.out.PaymentPersistencePort;
import com.hexagonal.payment.domain.kakaopay.KakaoPay;
import com.hexagonal.payment.infrastructure.jpa.entity.PaymentEntity;
import com.hexagonal.payment.infrastructure.jpa.entity.PaymentStatus;
import com.hexagonal.payment.infrastructure.jpa.entity.PaymentType;
import com.hexagonal.payment.infrastructure.jpa.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentPersistencePort {

	private final PaymentRepository paymentRepository;

	@Override
	public void createPayment(KakaoPay.ReadyResponse kakaoPay) {
		paymentRepository.save(PaymentEntity.from(kakaoPay));
	}

	@Override
	public PaymentEntity cancelPayment(Long paymentId) {
		return getPaymentById(paymentId);
	}

	@Override
	public void savePayment(KakaoPay.ApprovalResponse kakaoPay) {
		updatePayment(kakaoPay, getPaymentEntityByOrderId(kakaoPay.getPartner_order_id()));
	}

	private static void updatePayment(KakaoPay.ApprovalResponse kakaoPay, PaymentEntity paymentEntity) {
		paymentEntity.setAid(kakaoPay.getAid());
		paymentEntity.setPaymentType(PaymentType.getPaymentType(kakaoPay.getPayment_method_type()));
		paymentEntity.setTotalAmount(kakaoPay.getAmount().getTotal());
		paymentEntity.setPaymentStatus(PaymentStatus.SUCCESSED_PAYMENT);
	}

	@Override
	public PaymentEntity getPaymentEntity(String orderId) {
		return getPaymentEntityByOrderId(orderId);
	}

	private PaymentEntity getPaymentEntityByOrderId(String orderId) {
		return paymentRepository.findByOrderId(orderId).orElseThrow(() -> new RuntimeException("Payment not found!"));
	}

	private PaymentEntity getPaymentById(Long paymentId) {
		return paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found!"));
	}
}
