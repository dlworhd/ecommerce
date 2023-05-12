package com.hexagonal.seller.application.port.in;

import com.hexagonal.seller.domain.Seller;

public interface SellerUseCase {
	void createSeller(Seller.Create seller);
	void deleteSeller(Seller.Delete seller);
	void login(Seller.Login seller);
}
