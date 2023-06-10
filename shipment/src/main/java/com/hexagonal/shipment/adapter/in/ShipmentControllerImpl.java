package com.hexagonal.shipment.adapter.in;

import com.hexagonal.shipment.application.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShipmentControllerImpl implements ShipmentController {

	private final ShipmentService shipmentServiceAdapter;

	@Override
	public void createShipment(Long orderId) {
		shipmentServiceAdapter.createShipment(orderId);
	}

	@Override
	public void startShipment(Long shipmentId) {
		shipmentServiceAdapter.startShipment(shipmentId);

	}
}
