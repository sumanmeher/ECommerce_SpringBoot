package com.digit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digit.spring.payload.ProductDTO;
import com.digit.spring.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private UserService userService;

	// add to cart
	@PostMapping("/{uid}/{pid}")
	public ResponseEntity<String> addToCart(@PathVariable Long uid, @PathVariable Long pid) {
		return new ResponseEntity<>(userService.addToCart(uid, pid), HttpStatus.CREATED);
	}
	
	@GetMapping("/{uid}")
	public List<ProductDTO> getAllOneUser(@PathVariable Long uid){
		return userService.getAllOneUserCart(uid);
	}
	
	@DeleteMapping("/{uid}/{pid}") 
	public ResponseEntity<String> removeFromCart(@PathVariable Long uid, @PathVariable Long pid){
		return new ResponseEntity<>(userService.removeFromCart(uid, pid), HttpStatus.OK);
	} 


} 
