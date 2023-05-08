package com.hexagonal.payment.infrastructure.jpa.repository;

import com.hexagonal.payment.infrastructure.jpa.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

	Optional<PaymentEntity> findByOrderId(String orderId);
}
