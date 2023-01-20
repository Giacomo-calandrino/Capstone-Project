package com.opns.schede;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class SchedaController {
	
	@Autowired
	private SchedaService schedaService;
	
	// get all
	
	@GetMapping("/schede")
	public List<Scheda> getAllSchede(){
		return (List<Scheda>) schedaService.getAllSchede();
	}
	
	// get by id
	
	@GetMapping("/schede/{id}")
	public ResponseEntity<Scheda> getSchedaById(@PathVariable Long id) throws EntityNotFoundException {
		return schedaService.getSchedaById(id);
	}
	
	// post
	
	@PostMapping("/schede")
	public Scheda createScheda(@RequestBody Scheda scheda) {
		return schedaService.createScheda(scheda);
	}
	
	// put
	
	@PutMapping("/schede/{id}")
	public ResponseEntity<Scheda> updateScheda(@PathVariable Long id, @RequestBody Scheda schedaDetails) 
			throws EntityNotFoundException {
		return schedaService.updateScheda(id, schedaDetails);
	}
	
	// delete
	
	@DeleteMapping("/schede/{id}")
	public Map<String, Boolean> deleteScheda(@PathVariable Long id) throws EntityNotFoundException {
		return schedaService.deleteScheda(id);		
	}
	
	// get fino a un determinato volume
		
	@GetMapping("/schede/noSpoiler/{volume}")
	public List<Scheda> getSchedeNoSpoiler(@PathVariable int volume){
		return schedaService.getSchedeNoSpoiler(volume);
	}

}
