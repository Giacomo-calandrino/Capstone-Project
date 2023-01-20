package com.opns.schede;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SchedaService {
	
	@Autowired
	private SchedaRepository schedaRepository;
	
	// get all
	
	public List<Scheda> getAllSchede(){
		return (List<Scheda>) schedaRepository.findAll();
	}
	
	//get by id
	
	public ResponseEntity<Scheda> getSchedaById(Long id) throws EntityNotFoundException {
		Scheda scheda = schedaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Scheda " + id + " non trovata"));
		return ResponseEntity.ok().body(scheda);
	}
	
	// post
	
	public Scheda createScheda(Scheda scheda) {
		return schedaRepository.save(scheda);
	}
	
	// put
	
	public ResponseEntity<Scheda> updateScheda(Long id, Scheda schedaDetails) throws EntityNotFoundException {
		Scheda scheda = schedaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Scheda " + id + " non trovata"));
		scheda.setVolume(schedaDetails.getVolume());
		scheda.setTitolo(schedaDetails.getTitolo());
		scheda.setTesto(schedaDetails.getTesto());
		scheda.setImgUrl(schedaDetails.getImgUrl());
		final Scheda updatedScheda = schedaRepository.save(scheda);
		return ResponseEntity.ok(updatedScheda);
	}
	
	// delete
	
	public Map<String, Boolean> deleteScheda(Long id) throws EntityNotFoundException {
		Scheda scheda = schedaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Scheda " + id + " non trovata"));
		schedaRepository.delete(scheda);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	// get fino a un determinato volume
		
	public List<Scheda> getSchedeNoSpoiler(int volume){
		return (List<Scheda>) schedaRepository.findNoSpoiler(volume);
	}
	
}
