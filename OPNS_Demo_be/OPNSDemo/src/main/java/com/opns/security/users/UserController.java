package com.opns.security.users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// get all
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	// get by id
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(Long userId) throws Exception {
		return userService.getUserById(userId);		
	}
	
	// post
	
	@PostMapping("/users")
	public User createUser(User user) {
		return userService.createUser(user);
	}
	
	// put
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(Long userId, User userDetails) throws Exception {
		return userService.updateUser(userId, userDetails);
	}
	
	// delete
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(Long userId) throws Exception {
		return userService.deleteUser(userId);
	}

}
