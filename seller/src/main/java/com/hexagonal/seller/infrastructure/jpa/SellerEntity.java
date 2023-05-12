package com.hexagonal.seller.infrastructure.jpa;

import com.hexagonal.seller.domain.Seller;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "seller")
public class SellerEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "seller_id")
	private UUID id;

	private String email;
	private String password;
	private String registrationNum;


	@Enumerated(EnumType.STRING)
	private SellerStatus sellerStatus;

	public static SellerEntity from(Seller.Create seller, PasswordEncoder passwordEncoder){
		return SellerEntity.builder()
				.email(seller.getEmail())
				.password(passwordEncoder.encode(seller.getPassword()))
				.registrationNum(seller.getRegistrationNum())
				.sellerStatus(SellerStatus.ACTIVE)
				.build();
	}

//	public void update(Seller seller){
//		BeanUtils.copyProperties(seller, this);
//	}

}
