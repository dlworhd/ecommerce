package com.ecommerce.user.adapter.out;

import com.ecommerce.user.domain.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "user_id")
	private UUID id;

	private String email;
	private String password;

	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	public static UserEntity from(UserDto.Request user, PasswordEncoder passwordEncoder){
		return UserEntity.builder()
				.email(user.getEmail())
				.password(passwordEncoder.encode(user.getPassword()))
				.userStatus(UserStatus.ACTIVE)
				.build();
	}

//	public void update(User user){
//		BeanUtils.copyProperties(user, this);
//	}

}
