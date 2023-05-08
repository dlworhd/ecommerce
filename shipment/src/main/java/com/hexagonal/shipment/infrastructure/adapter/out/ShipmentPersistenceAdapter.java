package com.hexagonal.shipment.infrastructure.adapter.out;

import com.hexagonal.shipment.application.port.out.ShipmentPersistencePort;
import com.hexagonal.shipment.infrastructure.jpa.Shipment;
import com.hexagonal.shipment.infrastructure.jpa.ShipmentStatus;
import com.hexagonal.shipment.infrastructure.jpa.ShipmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShipmentPersistenceAdapter implements ShipmentPersistencePort {

	private final ShipmentRepository shipmentRepository;

	@Override
	@Transactional
	public void createShipment(Long orderId) {
		shipmentRepository.save(Shipment.builder().orderId(orderId).shipmentStatus(ShipmentStatus.READY).build());
	}

	@Override
	@Transactional
	public void startShipment(Long shipmentId) {
		Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(() -> new RuntimeException("Shipment not found!"));
		shipment.setShipmentStatus(ShipmentStatus.STARTED);
	}

}
