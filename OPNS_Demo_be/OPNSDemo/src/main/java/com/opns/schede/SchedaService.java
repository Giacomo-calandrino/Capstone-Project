package com.opns.schede;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class SchedaService {
	
	@Autowired
	private SchedaRepository schedaRepository;
	
	// get all
	
	public List<Scheda> getAllSchede(){
		return (List<Scheda>) schedaRepository.findAll();
	}
	
	//get by id
	
	public ResponseEntity<Scheda> getSchedaById(@PathVariable(value="id") Long schedaId) throws Exception {
		Scheda scheda = schedaRepository.findById(schedaId)
				.orElseThrow(() -> new Exception("Scheda " + schedaId + " non trovata"));
		return ResponseEntity.ok().body(scheda);
	}
	
	// post
	
	public Scheda createScheda(@RequestBody Scheda scheda) {
		return schedaRepository.save(scheda);
	}
	
	// put
	
	public ResponseEntity<Scheda> updateScheda(@PathVariable(value = "id") Long schedaId, 
			@RequestBody Scheda schedaDetails) throws Exception {
		Scheda scheda = schedaRepository.findById(schedaId)
				.orElseThrow(() -> new Exception("Scheda " + schedaId + " non trovata"));
		scheda.setVolume(schedaDetails.getVolume());
		scheda.setTitolo(schedaDetails.getTitolo());
		scheda.setTesto(schedaDetails.getTesto());
		scheda.setImgUrl(schedaDetails.getImgUrl());
		final Scheda updatedScheda = schedaRepository.save(scheda);
		return ResponseEntity.ok(updatedScheda);
	}
	
	// delete
	
	public Map<String, Boolean> deleteScheda(@PathVariable(value = "id") Long schedaId) throws Exception {
		Scheda scheda = schedaRepository.findById(schedaId)
				.orElseThrow(() -> new Exception("Scheda " + schedaId + " non trovata"));
		schedaRepository.delete(scheda);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	// get fino a un determinato volume
	
	public Page<Scheda> getSchedeFinoVolume(int volume, Pageable pageable){
		return (Page<Scheda>) schedaRepository.findFinoVolume(volume, pageable);
	}

}
