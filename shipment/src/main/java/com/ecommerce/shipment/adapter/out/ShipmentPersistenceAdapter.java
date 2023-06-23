package com.ecommerce.shipment.adapter.out;

import com.ecommerce.shipment.application.port.out.ShipmentPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShipmentPersistenceAdapter implements ShipmentPersistencePort {

	private final ShipmentRepository shipmentRepository;

	@Override
	public void createShipment(Long orderId) {
		shipmentRepository.save(Shipment.builder().orderId(orderId).shipmentStatus(ShipmentStatus.READY).build());
	}

	@Override
	public void startShipment(Long shipmentId) {
		Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(() -> new RuntimeException("Shipment not found!"));
		shipment.setShipmentStatus(ShipmentStatus.STARTED);
	}

}
