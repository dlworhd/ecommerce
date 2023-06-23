package com.ecommerce.order.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String>, OrderRepositoryCustom {
}
