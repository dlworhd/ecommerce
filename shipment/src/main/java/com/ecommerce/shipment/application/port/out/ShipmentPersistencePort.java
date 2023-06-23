package com.ecommerce.shipment.application.port.out;

public interface ShipmentPersistencePort {

	void createShipment(Long orderId);
	void startShipment(Long shipmentId);
}
