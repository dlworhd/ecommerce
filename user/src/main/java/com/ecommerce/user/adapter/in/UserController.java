package com.ecommerce.user.adapter.in;


import com.ecommerce.user.domain.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {

	@GetMapping("/users")
	ResponseEntity<?> users();

	@PostMapping("/users")
	ResponseEntity<?> createUser(@RequestBody UserDto.Request user);

//	@PutMapping("/users")
//	ResponseEntity<?> modifyUser(@RequestBody UserDto.Request user);

	@PutMapping("/users")
	ResponseEntity<?> deleteUser(@RequestBody UserDto.Request user);

	@PostMapping("/users/auth")
	ResponseEntity<?> login(@RequestBody UserDto.Request user);

}
