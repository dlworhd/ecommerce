//package com.hexagonal.order.infrastructure.jpa.repository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//
//import static com.hexagonal.order.infrastructure.jpa.QOrderEntity.orderEntity;
//import static com.hexagonal.order.infrastructure.jpa.QOrderItemEntity.orderItemEntity;
//
//@RequiredArgsConstructor
//public class OrderRepositoryImpl implements OrderRepositoryCustom{
//
//	private final JPAQueryFactory factory;
//
//	@Override
//	public void findOrderWithOrderItemsById(String orderId) {
//		factory.selectFrom(orderEntity).join(orderEntity.orderItemEntities, orderItemEntity).fetchJoin().where(orderEntity.id.eq(orderId)).fetch();
//	}
//}
