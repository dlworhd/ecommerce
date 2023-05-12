package com.hexagonal.seller.application.port.out;

import com.hexagonal.seller.domain.Seller;

public interface SellerPersistencePort {

	void createSeller(Seller.Create seller);
	void deleteSeller(Seller.Delete seller);

	void login(Seller.Login seller);
}
