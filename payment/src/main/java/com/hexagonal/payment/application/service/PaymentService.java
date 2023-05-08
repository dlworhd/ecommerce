package com.hexagonal.payment.application.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hexagonal.payment.application.config.LocalDateTimeDeserializer;
import com.hexagonal.payment.application.port.in.PaymentUseCase;
import com.hexagonal.payment.application.port.out.PaymentPersistencePort;
import com.hexagonal.payment.domain.kakaopay.KakaoPay;
import com.hexagonal.payment.infrastructure.jpa.entity.PaymentEntity;
import com.hexagonal.payment.infrastructure.jpa.entity.PaymentStatus;
import com.hexagonal.payment.infrastructure.jpa.entity.PaymentType;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {


	@Value("${kakao.cid}")
	private String cid;

	@Value("${kakao.admin-key}")
	private String admin_key;

	@Value("${kakao-payment-approval-server}")
	private String KAKAO_PAYMENT_APPROVAL_SERVER;

	@Value("${kakao-payment-cancel-server}")
	private String KAKAO_PAYMENT_CANCEL_SERVER;

	private Gson gson;

	private String FORMATTING_SUFFIX = ";charset=UTF-8";

	private final RestTemplate restTemplate;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final PaymentPersistencePort paymentPersistencePort;

	@PostConstruct
	public void init() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		gson = gsonBuilder.create();
	}

	@Override
	@Transactional
	public void createPayment(KakaoPay.ReadyResponse kakaoPay) {
		paymentPersistencePort.createPayment(kakaoPay);
	}

	@Override
	@Transactional
	public void approvePayment(String pgToken, String orderId) {

		PaymentEntity paymentEntity = paymentPersistencePort.getPaymentEntity(orderId);

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("cid", cid);
		params.add("tid", paymentEntity.getTid());
		params.add("partner_order_id", paymentEntity.getOrderId());
		//TODO: UserID 도메인 추가 후에 설정하기
		params.add("partner_user_id", " ");
		params.add("pg_token", pgToken);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", admin_key);
		headers.set("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + FORMATTING_SUFFIX);

		// Kakao에 데이터 전송 후 Response 받기
		ResponseEntity<String> response = restTemplate.postForEntity(KAKAO_PAYMENT_APPROVAL_SERVER, new HttpEntity<>(params, headers), String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			KakaoPay.ApprovalResponse approvalResponse = gson.fromJson(response.getBody(), KakaoPay.ApprovalResponse.class);

			paymentEntity.setAid(approvalResponse.getAid());
			paymentEntity.setPaymentType(PaymentType.getPaymentType(approvalResponse.getPayment_method_type()));
			paymentEntity.setPaymentStatus(PaymentStatus.SUCCESSED_PAYMENT);
			paymentEntity.setTotalAmount(approvalResponse.getAmount().getTotal());

			sendKafkaMessage("payment-completed", paymentEntity.getOrderId());

		} else {
			throw new RuntimeException("Failed save payment info!");
		}
	}

	private void sendKafkaMessage(String topic, String value) {
		kafkaTemplate.send(topic, value);
	}

	@Override
	@Transactional
	public void cancelPayment(Long paymentId) {
		PaymentEntity paymentEntity = paymentPersistencePort.cancelPayment(paymentId);
		paymentEntity.setPaymentStatus(PaymentStatus.CANCELD_PAYMENT);

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("cid", cid); //st
		params.add("tid", paymentEntity.getTid()); //st
		params.add("cancel_amount", paymentEntity.getTotalAmount()); //int
		params.add("cancel_tax_free_amount", 0); //int

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", admin_key);
		headers.set("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + FORMATTING_SUFFIX);

		// Kakao에 데이터 전송 후 Response 받기
		ResponseEntity<String> response = restTemplate.postForEntity(KAKAO_PAYMENT_CANCEL_SERVER, new HttpEntity<>(params, headers), String.class);

		sendKafkaMessage("payment-canceled", paymentEntity.getOrderId());


		if (response.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("Failed save payment info!");
		}

	}
}

