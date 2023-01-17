package com.opns.security.users;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.opns.security.roles.ERole;
import com.opns.security.roles.Role;


@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	// get user basic info
	
	public UserResponse getUserBasicInformations(String username) {
		User user = userRepository.findByUsername(username).get();
		return UserResponse
		.builder()
		.username(username)
		.volume(user.getVolume())
		.role( user.getRoles().stream().findFirst().get().getRoleName().name()).build();		
	}
	
	// get all
	
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	// get by id
	
	public ResponseEntity<User> getUserById(Long id) throws EntityNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Utente " + id + " non trovato"));
		return ResponseEntity.ok().body(user);		
	}
	
	// post
	
	public User createUser(User user) {
		if(userRepository.existsByUsername(user.getUsername())) {
			throw new EntityExistsException("Username esistente");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		user.addRole(new Role(ERole.ROLE_USER));
		return userRepository.save(user);
	}
	
	// put
	
	public ResponseEntity<User> updateUser(Long id, User userDetails) throws EntityNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Utente " + id + " non trovato"));		
		user.setNome(userDetails.getNome());
		user.setCognome(userDetails.getCognome());
		user.setEmail(userDetails.getEmail());
		user.setUsername(userDetails.getUsername());
		user.setVolume(userDetails.getVolume());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}	
	
	// patch
	
	public User patchUser(Long id, Map<String, Object> fields) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(User.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, user.get(), value);
			});
			return userRepository.save(user.get());
		}
		return null;
	}
	
	// delete
	
	public Map<String, Boolean> deleteUser(Long id) throws EntityNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Utente " + id + " non trovato"));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}	

}
