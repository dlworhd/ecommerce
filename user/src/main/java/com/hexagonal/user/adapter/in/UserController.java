package com.hexagonal.user.adapter.in;


import com.hexagonal.user.domain.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {

	@PostMapping("/users")
	ResponseEntity<?> createUser(@RequestBody UserDto.Request user);

//	@PutMapping("/users")
//	ResponseEntity<?> modifyUser(@RequestBody UserDto.Request user);

	@PutMapping("/users")
	ResponseEntity<?> deleteUser(@RequestBody UserDto.Request user);

	@PostMapping("/users/auth")
	ResponseEntity<?> login(@RequestBody UserDto.Request user);

}
