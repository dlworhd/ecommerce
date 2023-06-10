package com.hexagonal.shipment.adapter.in;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface ShipmentController {

	@PostMapping("/shipments/create/{orderId}")
	void createShipment(@PathVariable Long orderId);

	@PostMapping("/shipments/start/{shipmentId}")
	void startShipment(@PathVariable Long shipmentId);

}
