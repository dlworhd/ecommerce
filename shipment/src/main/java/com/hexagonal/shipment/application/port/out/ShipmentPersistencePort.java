package com.hexagonal.shipment.application.port.out;

public interface ShipmentPersistencePort {

	void createShipment(Long orderId);
	void startShipment(Long shipmentId);
}
