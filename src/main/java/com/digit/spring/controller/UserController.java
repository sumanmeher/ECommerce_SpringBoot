package com.digit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digit.spring.entity.User;
import com.digit.spring.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{uid}")
	public User getOneUser(@PathVariable Long uid) {
		return userService.getOneUser(uid);
	}
	
}
