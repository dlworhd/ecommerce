package com.hexagonal.user.infrastructure.adapter.in;


import com.hexagonal.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {

	@PostMapping("/users")
	void createUser(@RequestBody User user);

//	@PutMapping("/users")
//	void modifyUser(@RequestBody User user);

	@PutMapping("/users")
	void deleteUser(@RequestBody User user);

	@PostMapping("/users/auth")
	ResponseEntity<?> login(@RequestBody User user);

}
