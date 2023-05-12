package com.hexagonal.seller.infrastructure.adapter.in;

import com.hexagonal.seller.application.util.jwt.TokenProvider;
import com.hexagonal.seller.application.port.in.SellerUseCase;
import com.hexagonal.seller.domain.Token;
import com.hexagonal.seller.domain.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SellerControllerImpl implements SellerController {

	private final SellerUseCase sellerUseCase;
	private final TokenProvider tokenProvider;

	@Override
	public void createSeller(Seller.Create seller) {
		sellerUseCase.createSeller(seller);
	}

	@Override
	public void deleteSeller(Seller.Delete seller) {
		sellerUseCase.deleteSeller(seller);
	}

	@Override
	public ResponseEntity<?> login(Seller.Login seller) {
		sellerUseCase.login(seller);
		return ResponseEntity.ok(Token.builder().token(tokenProvider.createToken(seller.getEmail())).build());
	}
}
