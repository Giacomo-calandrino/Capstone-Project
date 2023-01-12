package com.opns.security.users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opns.security.roles.Role;

@RestController
@CrossOrigin(origins="*")
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
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(Long userId) throws Exception {
		return userService.getUserById(userId);		
	}
	
	// post
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	// put
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userDetails) throws Exception {
		return userService.updateUser(userId, userDetails);
	}
	
	// put volume
	/*
	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateVolume(@PathVariable Long userId, @RequestBody int volume) throws Exception {
		return userService.updateVolume(userId, volume);
	}
	*/
	
	@PatchMapping("/users/{id}")
	public User patchUser(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		return userService.patchUser(id, fields);
	}
	
	// delete
	
	@DeleteMapping("/users/{userId}")
	public Map<String, Boolean> deleteUser(@PathVariable Long userId) throws Exception {
		return userService.deleteUser(userId);
	}
	
	/*public ResponseEntity<String> addRole(Long id,Role role) {
		userService.addRole(id, role);
		return ResponseEntity.ok("Role has been added");
	}*/

}
