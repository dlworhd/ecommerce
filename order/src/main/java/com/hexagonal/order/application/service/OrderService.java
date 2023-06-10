package com.hexagonal.order.application.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hexagonal.order.adapter.out.OrderStatus;
import com.hexagonal.order.application.config.LocalDateTimeDeserializer;
import com.hexagonal.order.application.port.in.OrderUseCase;
import com.hexagonal.order.application.port.out.OrderPersistencePort;
import com.hexagonal.order.domain.KakaoPay;
import com.hexagonal.order.domain.OrderDto;
import com.hexagonal.order.domain.OrderInfo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

	@Value("${product-server}")
	private String PRODUCT_SERVER;

	@Value("${payment-server.url}")
	private String PAYMENT_SERVER;

	@Value("${payment-server.approval}")
	private String PAYMENT_SERVER_APPROVAL;

	@Value("${payment-server.fail}")
	private String PAYMENT_SERVER_FAIL;

	@Value("${payment-server.cancel}")
	private String PAYMENT_SERVER_CANCEL;

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
	private final KafkaTemplate<String, Map<Long, Long>> kafkaProductInventoryTemplate;

	@PostConstruct
	public void init() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		gson = gsonBuilder.create();
	}

	@Transactional
	@Override
	public OrderDto.Response createOrder(OrderDto.Request request) {
		OrderDto.Response order = orderPersistencePort.createOrder(request);

		String orderName = createOrderName(request);

		MultiValueMap<String, Object> params = paramBuilder(order, orderName);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", admin_key);
		headers.set("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + FORMATTING_SUFFIX);

		// Kakao에 데이터 전송 후 Response 받기
		KakaoPay.ReadyResponse readyResponse = gson.fromJson(restTemplate.postForEntity(
						KAKAO_PAYMENT_READY_SERVER, new HttpEntity<>(params, headers),
						String.class)
				.getBody(), KakaoPay.ReadyResponse.class);
		readyResponse.setOrderName(orderName);

		// Payment에 정보를 보내야 함(RestTemplate)
		paymentRequest(readyResponse);

		order.setPaymentUrl(readyResponse.getNext_redirect_pc_url());


		List<OrderDto.ProductInfo> productInfos = request.getProductInfos();

		Map<Long, Long> map = new HashMap<>();

		productInfos.stream()
				.forEach(product -> map.put(product.getId(), product.getQuantity()));

		kafkaProductInventoryTemplate.send("order-completed", map);
		return order;
	}

	@Transactional
	@Override
	public OrderDto.Response cancelOrder(String orderId) {
		return orderPersistencePort.cancelOrder(orderId);
	}

	@Transactional
	@Override
	public List<OrderInfo.Simple> getOrders(OrderDto.Request request) {
		return orderPersistencePort.getOrders(request);
	}

	@Transactional
	@KafkaListener(topics = "payment-completed")
	public void changeStatusOrdered(String orderId) {
		orderPersistencePort.changeStatus(orderId, OrderStatus.ORDERED);
	}

	@Transactional
	@KafkaListener(topics = "payment-canceled")
	public void changeStatusCanceled(String orderId) {
		orderPersistencePort.changeStatus(orderId, OrderStatus.CANCELLED);
	}

	private void paymentRequest(KakaoPay.ReadyResponse readyResponse) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> exchange = restTemplate.postForEntity(PAYMENT_SERVER + "/payments", new HttpEntity<>(readyResponse, headers), String.class);

		if (exchange.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("Failed saving tid!");
		}
	}

	private MultiValueMap<String, Object> paramBuilder(OrderDto.Response response, String orderName) {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("cid", cid);
		params.add("partner_order_id", response.getOrderId());
		params.add("partner_user_id", " ");
		params.add("item_name", orderName);
		params.add("quantity", response.getTotalQuantity());
		params.add("total_amount", response.getTotalAmount());
		params.add("tax_free_amount", 0);
		params.add("approval_url", PAYMENT_SERVER_APPROVAL + "?partner_order_id=" + response.getOrderId());
		params.add("fail_url", PAYMENT_SERVER_CANCEL);
		params.add("cancel_url", PAYMENT_SERVER_CANCEL);
		return params;
	}

	public String createOrderName(OrderDto.Request request) {
		List<Long> productIds = request.getProductInfos().stream().map(product -> product.getId()).collect(Collectors.toList());
		//TODO: productIds보내서 OrderName 만들기

		HttpEntity<List<Long>> httpEntity = new HttpEntity<>(productIds, null);
		return restTemplate.postForObject(PRODUCT_SERVER + "/products/name", httpEntity, String.class);
	}
}
