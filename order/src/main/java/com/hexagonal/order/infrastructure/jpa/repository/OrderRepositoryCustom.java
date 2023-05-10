package com.hexagonal.order.infrastructure.jpa.repository;

import com.hexagonal.order.infrastructure.jpa.entity.OrderEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.UUID;


public interface OrderRepositoryCustom {


	List<OrderEntity> findOrderWithOrderItemsById(UUID userId);
}
