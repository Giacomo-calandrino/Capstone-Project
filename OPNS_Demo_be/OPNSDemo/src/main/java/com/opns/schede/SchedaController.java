package com.opns.schede;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
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
	public ResponseEntity<Scheda> getSchedaById(Long schedaId) throws Exception {
		return schedaService.getSchedaById(schedaId);
	}
	
	// post
	
	@PostMapping("/schede")
	public Scheda createScheda(Scheda scheda) {
		return schedaService.createScheda(scheda);
	}
	
	// put
	
	@PutMapping("/schede/{id}")
	public ResponseEntity<Scheda> updateScheda(Long schedaId, Scheda schedaDetails) throws Exception {
		return schedaService.updateScheda(schedaId, schedaDetails);
	}
	
	// delete
	
	@DeleteMapping("/schede/{id}")
	public Map<String, Boolean> deleteScheda(Long schedaId) throws Exception {
		return schedaService.deleteScheda(schedaId);		
	}
	
	// get fino a un determinato volume
	
	@GetMapping("/schede/schedelimit/{vol}")
	public Page<Scheda> getSchedaFinoVolume(int vol, Pageable pageable) throws Exception {
		return schedaService.getSchedeFinoVolume(vol, pageable);
	}

}
