package com.ecommerce.shipment.adapter.out;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class Shipment {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderId;

	@Enumerated(EnumType.STRING)
	private ShipmentStatus shipmentStatus;

}
