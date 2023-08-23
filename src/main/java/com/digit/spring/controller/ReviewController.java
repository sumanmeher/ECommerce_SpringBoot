package com.digit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digit.spring.payload.ProductDTO;
import com.digit.spring.payload.ReviewDTO;
import com.digit.spring.service.ReviewService;

@Controller
@RequestMapping("/api/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

//	@GetMapping("/{rid}")
//	public ReviewDTO getOneReview(@PathVariable Long rid) {
//		return reviewService.getSpecificReview(rid);
//	}
	
	@GetMapping("/{rid}")
	public ResponseEntity<ReviewDTO> getOneReview(@PathVariable Long rid) {
	    ReviewDTO reviewDTO = reviewService.getSpecificReview(rid);
	    
	    if (reviewDTO != null) {
	        return ResponseEntity.ok(reviewDTO);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}
	
	// add comments
	@PostMapping
	public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDto) {
		ReviewDTO review = reviewService.addReview(reviewDto);
		return new ResponseEntity<>(review, HttpStatus.CREATED);
	}


//	@GetMapping("/{rid}")
//	public ResponseEntity<String> getReview(@PathVariable Long rid) {
////	        ReviewDTO review = reviewService.addReview(reviewDto);
//		return new ResponseEntity<>("review success", HttpStatus.CREATED);
//	}
	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getAllReviews() {
	    List<ReviewDTO> reviewDTOList = reviewService.getAllReviews();
	    
	    if (!reviewDTOList.isEmpty()) {
	        return ResponseEntity.ok(reviewDTOList);
	    } else {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(reviewDTOList);
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ReviewDTO> updateReviews(@PathVariable Long id, @RequestBody ReviewDTO reviewDto) {
		return new ResponseEntity<>(reviewService.updateReviews(id, reviewDto), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long id) {
	    try {
	        String deleteMessage = reviewService.deleteReview(id);
	        return ResponseEntity.ok(deleteMessage);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
	    }
	}

}
