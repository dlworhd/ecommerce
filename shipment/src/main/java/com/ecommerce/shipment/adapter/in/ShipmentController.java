package com.ecommerce.shipment.adapter.in;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface ShipmentController {

	@PostMapping("/shipments/create/{orderId}")
	void createShipment(@PathVariable Long orderId);

	@PostMapping("/shipments/start/{shipmentId}")
	void startShipment(@PathVariable Long shipmentId);

}
