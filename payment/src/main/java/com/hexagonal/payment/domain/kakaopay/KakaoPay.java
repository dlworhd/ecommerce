package com.hexagonal.payment.domain.kakaopay;

import com.hexagonal.payment.adapter.out.PaymentEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class KakaoPay {

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ReadyResponse {
		private String orderId;
		private String orderName;
		private String tid;
		private String next_redirect_pc_url;
		private LocalDateTime createdAt;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ApprovalResponse {
		@Value("${kakao.cid}")
		private String cid; // 가맹점 코드
		private String aid; // 요청 고유 번호
		private String tid; // 결제 고유 번호
		private String orderName; // 결제 고유 번호
		private String item_Name; // 결제 고유 번호
		private String pg_token; // 결제 승인 요청 인증 토큰
		private String partner_order_id; // 가맹점 주문 번호
		private String partner_user_id; // 가맹점 회원 ID
		private String payment_method_type;
		private String next_redirect_pc_url;
		private Integer quantity;
		private Long cancel_amount;
		private Long cancel_tax_free_amount;
		private LocalDateTime created_at;
		private LocalDateTime approved_at;
		private Extras extras;
		private Amount amount;
		private ApprovedCancelAmount approved_cancel_amount;
	}

	@Setter
	@Getter
	public static class Amount {
		private Long total;
		private Long tax_free;
		private Long vat;
		private Long point;
		private Long discount;
		private Long green_deposit;
	}

	@Getter
	@Setter
	public static class Extras {
		private String method_result_code;
		private String method_result_message;
	}

	@Getter
	@Setter
	public static class ApprovedCancelAmount {
		private int total;
		private int tax_free;
		private int vat;
		private int point;
		private int discount;
		private int green_deposit;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	public static class CancelInfo{
		private String cid;
		private String tid;
		private Long cancel_amount;
		private Long cancel_tax_free_amount;


		public static CancelInfo from(PaymentEntity paymentEntity){
			return CancelInfo.builder()
					.tid(paymentEntity.getTid())
					.cancel_amount(paymentEntity.getTotalAmount())
					.cancel_tax_free_amount(0L)
					.build();

		}
	}
}
