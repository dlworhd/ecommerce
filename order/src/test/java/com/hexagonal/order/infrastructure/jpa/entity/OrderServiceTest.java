package com.hexagonal.order.infrastructure.jpa.entity;

import com.hexagonal.order.application.port.out.OrderPersistencePort;
import com.hexagonal.order.application.service.OrderService;
import com.hexagonal.order.domain.OrderDto;
import com.hexagonal.order.infrastructure.jpa.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@Mock
	RestTemplate restTemplate;

	@Mock
	OrderPersistencePort orderPersistencePort;

	@InjectMocks
	OrderService orderService;

	@Test
	void createOrder() {
		OrderDto.ProductInfo productInfo = OrderDto.ProductInfo.builder()
				.id(1L)
				.price(1000L)
				.quantity(10L)
				.build();

		List<OrderDto.ProductInfo> productInfoList = new ArrayList<>();
		productInfoList.add(productInfo);


		OrderDto.Request request = OrderDto.Request.builder()
				.name("j")
				.addr("관악구")
				.userId(UUID.randomUUID())
				.phoneNum("01045992369")
				.productInfos(productInfoList)
				.build();

		when(orderPersistencePort.createOrder(request)).thenReturn(OrderDto.Response.builder()
				.orderName("나이키")
				.orderId("GIN20230514-01010101")
				.totalQuantity(10L)
				.totalAmount(10000L)
				.paymentUrl("http://localhost:8080/")
				.build()
		);

		OrderDto.Response response = orderPersistencePort.createOrder(request);
		Assertions.assertThat(response.getOrderId()).isEqualTo("GIN20230514-01010101");
		Assertions.assertThat(response.getOrderName()).isEqualTo("나이키");
	}

	@Test
	void cancelOrder() {
		OrderEntity orderEntity = OrderEntity.builder().id("GIN20230514-01010101").orderStatus(OrderStatus.ORDERED).orderItemEntities(new ArrayList<>()).build();

		when(orderService.cancelOrder(orderEntity.getId())).thenReturn(OrderDto.Response.from(OrderEntity.builder()
				.id("GIN20230514-01010101")
				.orderStatus(OrderStatus.CANCELED)
				.orderItemEntities(new ArrayList<>())
				.build())
		);
		OrderDto.Response response = orderService.cancelOrder(orderEntity.getId());

		Assertions.assertThat(response.getOrderStatus()).isEqualTo(OrderStatus.CANCELED);


	}


}