package com.hexagonal.order.infrastructure.jpa.repository;

import com.hexagonal.order.infrastructure.jpa.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>{
}
