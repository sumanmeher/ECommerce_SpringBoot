package com.digit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digit.spring.entity.User;
import com.digit.spring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;

	public User getOneUser(Long uid) {
		 return userRepo.getReferenceById(uid);
	}
}
