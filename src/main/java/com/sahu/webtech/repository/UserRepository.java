package com.sahu.webtech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sahu.webtech.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);
		
}
