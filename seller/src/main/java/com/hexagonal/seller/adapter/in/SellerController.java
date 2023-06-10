package com.hexagonal.seller.adapter.in;


import com.hexagonal.seller.domain.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SellerController {

	@PostMapping("/sellers")
	void createSeller(@RequestBody Seller.Create seller);

	@PutMapping("/sellers")
	void deleteSeller(@RequestBody Seller.Delete seller);

	@PostMapping("/sellers/auth")
	ResponseEntity<?> login(@RequestBody Seller.Login seller);

}
