package com.hexagonal.seller.application.service;

import com.hexagonal.seller.application.port.in.SellerUseCase;
import com.hexagonal.seller.application.port.out.SellerPersistencePort;
import com.hexagonal.seller.domain.Seller;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService implements SellerUseCase {

	private final SellerPersistencePort sellerPersistencePort;

	@Override
	@Transactional
	public void createSeller(Seller.Create seller) {
		sellerPersistencePort.createSeller(seller);
	}

	@Override
	@Transactional
	public void deleteSeller(Seller.Delete seller) {
		sellerPersistencePort.deleteSeller(seller);
	}

	@Override
	public void login(Seller.Login seller) {
		sellerPersistencePort.login(seller);
	}
}
