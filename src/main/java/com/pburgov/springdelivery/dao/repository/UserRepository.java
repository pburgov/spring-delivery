package com.pburgov.springdelivery.dao.repository;

import com.pburgov.springdelivery.dao.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	public User findByUsername(String username);
	
}
