package com.hexagonal.order.infrastructure.jpa.repository;

import com.hexagonal.order.infrastructure.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String>, OrderRepositoryCustom {
}
