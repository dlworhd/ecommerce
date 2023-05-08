package com.hexagonal.order.infrastructure.jpa.repository;

import com.hexagonal.order.infrastructure.jpa.entity.OrderSequence;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderSequenceRepository extends JpaRepository<OrderSequence, Long> {

}
