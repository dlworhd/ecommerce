package com.hexagonal.user.application.service;

import com.hexagonal.user.domain.UserDto;
import com.hexagonal.user.infrastructure.jpa.UserEntity;
import com.hexagonal.user.infrastructure.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * MySQL DB를 사용하고 있는 경우에 build.gradle에 H2 DB를 의존성 추가하면
 *
 * @DataJpaTest 별도의 Properties 설정 없이도 실행 잘 됨(@Transactional 자동 롤백 적용)
 */
@DataJpaTest
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void createUser() {

		//given
		UserEntity userEntity = UserEntity.from(
				UserDto.Request.builder()
						.email("dlwodud821@gmail.com")
						.password("1234")
						.build()
		);
		//when
		UserEntity savedUser = userRepository.save(userEntity);
		//then
		assertEquals(savedUser.getEmail(), userEntity.getEmail());
	}
}