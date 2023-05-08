package com.hexagonal.shipment.application.service;

import com.hexagonal.shipment.application.port.in.ShipmentUseCase;
import com.hexagonal.shipment.application.port.out.ShipmentPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentService implements ShipmentUseCase {

	private final ShipmentPersistencePort shipmentPersistencePort;

	@Override
	public void createShipment(Long orderId) {
		shipmentPersistencePort.createShipment(orderId);
	}

	@Override
	public void startShipment(Long shipmentId) {
		shipmentPersistencePort.startShipment(shipmentId);
	}
}
