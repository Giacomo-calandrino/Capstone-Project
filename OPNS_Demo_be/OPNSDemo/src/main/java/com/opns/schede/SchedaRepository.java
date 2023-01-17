package com.opns.schede;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchedaRepository extends PagingAndSortingRepository<Scheda, Long> {
			
	@Query(value="select s from Scheda s where s.volume <= ?1 order by s.volume")
	Page<Scheda> findFinoVolume(int volume, Pageable pageable);

}
