package com.opns.schede;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchedaRepository extends PagingAndSortingRepository<Scheda, Long> {
	
	//Page<Scheda> findByVolume(int volume, Pageable pageable);
	
	@Query(value="select s from schede where s.volume <= ?1")
	Page<Scheda> findFinoVolume(int volume, Pageable pageable);

}
