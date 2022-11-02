package com.opns.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.opns.models.User;
import com.opns.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// get all
	
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	// get by id
	
	public ResponseEntity<User> getUserById(@PathVariable(value="id") Long userId) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(() -> new Exception("Utente " + userId + " non trovato"));
		return ResponseEntity.ok().body(user);		
	}
	
	// post
	
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	// put
	
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, 
			@RequestBody User userDetails) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(() -> new Exception("Utente " + userId + " non trovato"));
		user.setNome(userDetails.getNome());
		user.setCognome(userDetails.getCognome());
		user.setEmail(userDetails.getEmail());
		user.setUsername(userDetails.getUsername());
		user.setVolume(userDetails.getVolume());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	// delete
	
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(() -> new Exception("Utente " + userId + " non trovato"));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
