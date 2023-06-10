package com.hexagonal.order.adapter.out;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import static com.hexagonal.order.adapter.out.QOrderEntity.orderEntity;
import static com.hexagonal.order.adapter.out.QOrderItemEntity.orderItemEntity;


@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{

	private final JPAQueryFactory factory;

	@Override
	public List<OrderEntity> findOrderWithOrderItemsById(UUID userId) {
		return factory.selectFrom(orderEntity).join(orderEntity.orderItemEntities, orderItemEntity).fetchJoin().where(orderEntity.userId.eq(userId)).fetch();
	}
}
