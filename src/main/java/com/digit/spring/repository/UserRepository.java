package com.digit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digit.spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	
}
