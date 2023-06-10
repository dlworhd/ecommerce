package com.hexagonal.seller.adapter.out;

import com.hexagonal.seller.application.port.out.SellerPersistencePort;
import com.hexagonal.seller.domain.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellerPersistenceAdapter implements SellerPersistencePort {

	private final PasswordEncoder passwordEncoder;
	private final SellerRepository sellerRepository;

	@Override
	public void createSeller(Seller.Create seller) {
		isExistSellerByEmail(seller.getEmail());
		isExistRegistrationNumber(seller.getRegistrationNum());
		sellerRepository.save(SellerEntity.from(seller, passwordEncoder));
	}

	private void isExistSellerByEmail(String email) {
		if (sellerRepository.existsByEmail(email)) throw new RuntimeException("Duplicated user!");
	}

	private void isExistRegistrationNumber(String registrationNumber) {
		if (sellerRepository.existsByRegistrationNum(registrationNumber)) throw new RuntimeException("Duplicated registration number!");
	}

	private SellerEntity getUserEntityByEmail(String email) {
		return sellerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Override
	public void deleteSeller(Seller.Delete seller) {
		SellerEntity sellerEntity = getUserEntityByEmail(seller.getEmail());
		sellerEntity.setSellerStatus(SellerStatus.UNACTIVE);
	}

	@Override
	public void login(Seller.Login seller) {
		SellerEntity sellerEntity = getUserEntityByEmail(seller.getEmail());
		if (!passwordEncoder.matches(seller.getPassword(), sellerEntity.getPassword())) {
			throw new RuntimeException("Password not equal");
		}
	}
}
