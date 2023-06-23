package com.ecommerce.shipment.application.port.in;

public interface ShipmentUseCase {

	void createShipment(Long orderId);
	void startShipment(Long orderId);
}
