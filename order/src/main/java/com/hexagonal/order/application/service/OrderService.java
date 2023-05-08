package com.hexagonal.order.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hexagonal.order.application.port.in.OrderUseCase;
import com.hexagonal.order.application.port.out.OrderPersistencePort;
import com.hexagonal.order.domain.KakaoPay;
import com.hexagonal.order.application.config.LocalDateTimeDeserializer;
import com.hexagonal.order.domain.Order;
import com.hexagonal.order.domain.OrderInfo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

	@Value("${payment-server}")
	private String PAYMENT_SERVER;

	@Value("${kakao-payment-ready-server}")
	private String KAKAO_PAYMENT_READY_SERVER;

	@Value("${kakao.cid}")
	private String cid;
	@Value("${kakao.admin-key}")
	private String admin_key;
	private Gson gson;

	private String FORMATTING_SUFFIX = ";charset=UTF-8";

	private final RestTemplate restTemplate;
	private final OrderPersistencePort orderPersistencePort;

	@PostConstruct
	public void init() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		gson = gsonBuilder.create();
	}

	@Override
	public String createOrder(Order order){
		OrderInfo orderInfo = orderPersistencePort.createOrder(order);

		MultiValueMap<String, Object> params = paramBuilder(orderInfo);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", admin_key);
		headers.set("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + FORMATTING_SUFFIX);

		// Kakao에 데이터 전송 후 Response 받기
		KakaoPay.ReadyResponse readyResponse = gson.fromJson(restTemplate.postForEntity(
						KAKAO_PAYMENT_READY_SERVER, new HttpEntity<>(params, headers),
						String.class)
				.getBody(), KakaoPay.ReadyResponse.class);
		readyResponse.setOrderId(orderInfo.getOrderId());
		readyResponse.setOrderName(orderInfo.getOrderName());

		// Payment에 정보를 보내야 함(RestTemplate)
		paymentRequest(readyResponse);

		return readyResponse.getNext_redirect_pc_url();
	}

	@Override
	public OrderInfo cancelOrder(String orderId) {
		return orderPersistencePort.cancelOrder(orderId);
	}

	@Override
	public OrderInfo getOrderInfoById(String orderId) {
		return orderPersistencePort.getOrder(orderId);
	}

	private void paymentRequest(KakaoPay.ReadyResponse readyResponse) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> exchange = restTemplate.postForEntity(PAYMENT_SERVER + "/payments", new HttpEntity<>(readyResponse, headers), String.class);

		if (exchange.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("Failed saving tid!");
		}
	}

	private MultiValueMap<String, Object> paramBuilder(OrderInfo orderInfo) {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("cid", cid);
		params.add("partner_order_id", orderInfo.getOrderId());
		params.add("partner_user_id", " ");
		params.add("item_name", orderInfo.getOrderName());
		params.add("quantity", orderInfo.getTotalQuantity());
		params.add("total_amount", orderInfo.getTotalAmount());
		params.add("tax_free_amount", 0);
		params.add("approval_url", PAYMENT_SERVER + "/payments" + "/approval?partner_order_id=" + orderInfo.getOrderId());
		params.add("fail_url", PAYMENT_SERVER + "/payments" + "/fail");
		params.add("cancel_url", PAYMENT_SERVER + "/payments" + "/cancel");
		return params;
	}


}
