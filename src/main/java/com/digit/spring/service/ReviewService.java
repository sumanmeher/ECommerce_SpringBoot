package com.digit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digit.spring.entity.Products;
import com.digit.spring.entity.Reviews;
import com.digit.spring.payload.ProductDTO;
import com.digit.spring.payload.ReviewDTO;
import com.digit.spring.repository.ProductRepository;
import com.digit.spring.repository.ReviewRepository;
import com.digit.spring.repository.UserRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepo;
	 
	private ReviewDTO EntityToDtoReview(Reviews review) {
		ReviewDTO reviewDto = new ReviewDTO();
		reviewDto.setRid(review.getRid());
		reviewDto.setText(review.getText());
		reviewDto.setPid(review.getProduct().getPid());
		reviewDto.setUid(review.getUser().getUid());
		return reviewDto;
	}
	
	private Reviews DtoToEntityReview(ReviewDTO reviewDto) {
		Reviews review = new Reviews();
		review.setRid(reviewDto.getRid());
		review.setText(reviewDto.getText());
		review.setProduct(productRepo.getReferenceById(reviewDto.getPid()));
		review.setUser(userRepository.getReferenceById(reviewDto.getUid()));
		
//		review.setPid(review.getProduct().getPid());
//		review.setUid(review.getUser().getUid());
		return review;
	}
	
	
	public ReviewDTO addReview(ReviewDTO reviewDto) {
		Reviews review = reviewRepository.save(DtoToEntityReview(reviewDto));
		return EntityToDtoReview(review);
	}
	
	public ReviewDTO getSpecificReview(Long id) {
		Reviews review = reviewRepository.getReferenceById(id);
		return EntityToDtoReview(review); 
	}
	
	
	
}
