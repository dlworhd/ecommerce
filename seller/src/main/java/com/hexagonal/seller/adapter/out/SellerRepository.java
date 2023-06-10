package com.hexagonal.seller.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
	Optional<SellerEntity> findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByRegistrationNum(String registrationNum);
}
