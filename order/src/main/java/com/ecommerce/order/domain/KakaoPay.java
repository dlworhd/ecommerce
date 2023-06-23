package com.ecommerce.order.domain;

import lombok.*;

import java.time.LocalDateTime;

public class KakaoPay {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ReadyRequest {
		private String item_name; // 이름
		private String tid; // 결제 고유 번호
		private String cid; // 가맹점 코드
		private String msg; // 메시지
		private String pg_token; // 결제 승인 요청 인증 토큰
		private String partner_order_id; // 가맹점 주문 번호
		private String partner_user_id; // 가맹점 회원 ID
		private Long quantity; // 수량
		private Long total_amount; // 금액
		private Long tax_free_amount; // 세제액
		private Long cancel_amount;
		private Long cancel_tax_free_amount;

	}


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
}
