package com.opns.schede;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@GetMapping("/schede/{schedaId}")
	public ResponseEntity<Scheda> getSchedaById(@PathVariable Long schedaId) throws EntityNotFoundException {
		return schedaService.getSchedaById(schedaId);
	}
	
	// post
	
	@PostMapping("/schede")
	public Scheda createScheda(@RequestBody Scheda scheda) {
		return schedaService.createScheda(scheda);
	}
	
	// put
	
	@PutMapping("/schede/{schedaId}")
	public ResponseEntity<Scheda> updateScheda(@PathVariable Long schedaId, @RequestBody Scheda schedaDetails) 
			throws EntityNotFoundException {
		return schedaService.updateScheda(schedaId, schedaDetails);
	}
	
	// delete
	
	@DeleteMapping("/schede/{schedaId}")
	public Map<String, Boolean> deleteScheda(@PathVariable Long schedaId) throws EntityNotFoundException {
		return schedaService.deleteScheda(schedaId);		
	}
	
	// get fino a un determinato volume
	
	
	@GetMapping("/schede/limit/{vol}")
	public Page<Scheda> getSchedeFinoVolume(@PathVariable int vol, Pageable pageable){
		return schedaService.getSchedeFinoVolume(vol, pageable);
	}
	
	
	@GetMapping("/schede/noSpoiler/{vol}")
	public List<Scheda> getSchedeNoSpoiler(@PathVariable int vol){
		return schedaService.getSchedeNoSpoiler(vol);
	}

}
