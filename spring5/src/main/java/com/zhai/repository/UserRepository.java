package com.zhai.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zhai.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
}
