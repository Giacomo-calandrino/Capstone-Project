package com.opns.schede;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedaRepository extends JpaRepository<Scheda, Long> {	
	
	@Query(value="select s from Scheda s where s.volume <= ?1 order by s.volume")
	List<Scheda> findNoSpoiler(int volume);

}
