package com.hexagonal.user.infrastructure.jpa;

import com.hexagonal.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

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

	public static UserEntity from(User user){
		return UserEntity.builder()
				.email(user.getEmail())
				.password(user.getPassword())
				.userStatus(UserStatus.ACTIVE)
				.build();
	}

	public void update(User user){
		BeanUtils.copyProperties(user, this);
	}

}
