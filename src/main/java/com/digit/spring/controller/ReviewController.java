package com.digit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digit.spring.payload.ReviewDTO;
import com.digit.spring.service.ReviewService;

@Controller
@RequestMapping("/api/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	//add comments
	@PostMapping
	public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDto){
		ReviewDTO review = reviewService.addReview(reviewDto);
		return new ResponseEntity<>(review,HttpStatus.CREATED);
	}

}
