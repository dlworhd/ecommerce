package com.ecommerce.shipment.application.service;

import com.ecommerce.shipment.application.port.in.ShipmentUseCase;
import com.ecommerce.shipment.application.port.out.ShipmentPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentService implements ShipmentUseCase {

	private final ShipmentPersistencePort shipmentPersistencePort;

	@Override
	@Transactional
	public void createShipment(Long orderId) {
		shipmentPersistencePort.createShipment(orderId);
	}

	@Override
	@Transactional
	public void startShipment(Long shipmentId) {
		shipmentPersistencePort.startShipment(shipmentId);
	}
}
