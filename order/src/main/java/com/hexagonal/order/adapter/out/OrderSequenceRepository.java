package com.hexagonal.order.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderSequenceRepository extends JpaRepository<OrderSequence, Long> {

}
