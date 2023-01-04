package com.opns.security.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.opns.security.config.WebSecurityConfig;
import com.opns.security.roles.ERole;
import com.opns.security.roles.Role;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// get user basics info
	
	public UserResponse getUserBasicInformations(String userName) {
		User user = userRepository.findByUsername(userName).get();
		return UserResponse
		.builder()
		.username(userName)
		.volume(user.getVolume())
		.role( user.getRoles().stream().findFirst().get().getRoleName().name()).build();		
	}
	
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
		WebSecurityConfig p = new WebSecurityConfig();
		BCryptPasswordEncoder a = (BCryptPasswordEncoder)p.passwordEncoder();
		user.setPassword(a.encode(user.getPassword()));
		user.addRole(new Role(ERole.ROLE_USER));
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
	
	// put volume
	
	public ResponseEntity<User> updateVolume(@PathVariable(value = "id") Long userId, 
			@RequestBody int volume) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(() -> new Exception("Utente " + userId + " non trovato"));
		user.setNome(user.getNome());
		user.setCognome(user.getCognome());
		user.setEmail(user.getEmail());
		user.setUsername(user.getUsername());
		user.setVolume(volume);
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
	
	public User findById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("User not found");
		}
		return userRepository.findById(id).get();
	}
	
	public User refresh(Long id, User user) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("User not found");
		}
	
		return userRepository.save(user);
		
	}
	
	
	public void addRole(Long id,Role role) {
		User u = findById(id);
		u.addRole(role);
		refresh(id, u);
	}

}
