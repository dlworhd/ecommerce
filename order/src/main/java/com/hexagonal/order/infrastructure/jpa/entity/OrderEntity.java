package com.hexagonal.order.infrastructure.jpa.entity;

import com.hexagonal.order.domain.Order;
import com.hexagonal.order.infrastructure.jpa.OrderGenerator;
import com.hexagonal.order.infrastructure.jpa.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {

	@Id
	@Column(name = "order_id")
	private String id;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@OneToMany(mappedBy = "order")
	List<OrderItemEntity> orderItemEntities;

	private String addr;
	private String name;
	private String phoneNum;

	public static OrderEntity from(Order order, OrderGenerator orderGenerator) {
		return OrderEntity.builder()
				.id(orderGenerator.generateOrderId())
				.addr(order.getAddr())
				.phoneNum(order.getPhoneNum())
				.name(order.getName())
				.orderStatus(OrderStatus.NOT_ORDERED)
				.build();
	}

	public void addOrderItemEntities(OrderItemEntity orderItemEntity) {
		if (this.orderItemEntities == null) {
			this.orderItemEntities = new ArrayList<>();
		}
		orderItemEntity.setOrder(this);
		this.orderItemEntities.add(orderItemEntity);
	}

	public String getOrderName(){
		String customOrderName = "";

		if (this.orderItemEntities != null && this.orderItemEntities.size() > 0) {
			customOrderName = this.orderItemEntities.size() > 1 ?
					this.orderItemEntities.get(0).getProductName() + " 외 " + (this.orderItemEntities.size() - 1) + "개" :
					this.orderItemEntities.get(0).getProductName();
		}
		return customOrderName;
	}

	public Long getTotalAmount() {
		return this.orderItemEntities.stream()
				.mapToLong(orderItemEntity -> orderItemEntity.getTotalAmount())
				.sum();
	}

	public Long getTotalQuantity() {
		return this.orderItemEntities.stream()
				.mapToLong(orderItemEntity -> orderItemEntity.getTotalQuantity())
				.sum();
	}

}
