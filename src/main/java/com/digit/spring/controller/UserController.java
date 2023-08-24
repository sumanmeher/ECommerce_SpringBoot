package com.digit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digit.spring.payload.UserDTO;
import com.digit.spring.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Register user
	@PostMapping("auth/register")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDto){
		return new ResponseEntity<>(userService.addProduct(userDto), HttpStatus.CREATED);
	}
	
	//Login user
	@PostMapping("auth/long/{uid}")
	public ResponseEntity<UserDTO> getOneUser(@PathVariable Long uid, @RequestBody UserDTO userDto) {
		UserDTO oneUser = userService.getOneUser(uid,userDto);
		
		if(oneUser!=null)
			return new ResponseEntity<>(oneUser, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	
	
}
