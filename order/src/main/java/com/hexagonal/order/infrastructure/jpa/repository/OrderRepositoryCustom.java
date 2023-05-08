package com.hexagonal.order.infrastructure.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;


public interface OrderRepositoryCustom {


	void findOrderWithOrderItemsById(String orderId);
}
