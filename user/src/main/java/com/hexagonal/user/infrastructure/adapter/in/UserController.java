package com.hexagonal.user.infrastructure.adapter.in;


import com.hexagonal.user.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {

	@PostMapping("/users")
	void createUser(@RequestBody User user);

	@PutMapping("/users")
	void modifyUser(@RequestBody User user);

	@PutMapping("/users")
	void deleteUser(@RequestBody User user);

}
