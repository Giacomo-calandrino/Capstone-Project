package com.opns.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.opns.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
		
}
