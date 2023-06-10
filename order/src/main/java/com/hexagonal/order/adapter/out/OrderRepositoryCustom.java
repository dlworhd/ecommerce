package com.hexagonal.order.adapter.out;

import java.util.List;
import java.util.UUID;


public interface OrderRepositoryCustom {


	List<OrderEntity> findOrderWithOrderItemsById(UUID userId);
}
