package com.hexagonal.payment.adapter.out;

import com.hexagonal.payment.domain.kakaopay.KakaoPay;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String orderId;
	private String orderName;
	@Column(unique = true)
	private String tid;
	private String aid;

	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	private Long totalAmount;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;


	public static PaymentEntity from(KakaoPay.ReadyResponse kakaoPay) {
		return PaymentEntity.builder()
				.orderId(kakaoPay.getOrderId())
				.orderName(kakaoPay.getOrderName())
				.paymentStatus(PaymentStatus.READY_PAYMENT)
				.tid(kakaoPay.getTid())
				.build();
	}
}
