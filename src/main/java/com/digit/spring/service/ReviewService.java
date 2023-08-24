package com.digit.spring.service;

import java.util.List;
import java.util.stream.Collectors;

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
		review.setUser(userRepository.getReferenceById(reviewDto.getUid()));
		review.setProduct(productRepo.getReferenceById(reviewDto.getPid()));
		return review;
	}
	
	public ReviewDTO getSpecificReview(Long id) {
		Reviews review = reviewRepository.getReferenceById(id);
		return EntityToDtoReview(review); 
	}
	public ReviewDTO addReview(ReviewDTO reviewDto) {
		Reviews review = reviewRepository.save(DtoToEntityReview(reviewDto));
		return EntityToDtoReview(review);
	}

	public List<ReviewDTO> getAllReviews(Long uid) {
		List<Reviews> review = productRepo.getReferenceById(uid).getReviews();
		return review.stream().map(reviews -> EntityToDtoReview(reviews)).collect(Collectors.toList());
	}

	public ReviewDTO updateReviews(Long id, ReviewDTO reviewDto) {
		Reviews review = reviewRepository.getReferenceById(id);
		if(reviewDto.getText()!=null)
			review.setText(reviewDto.getText());
		
		Reviews save =  reviewRepository.save(review);
		return EntityToDtoReview(save);
	}

	public String deleteReview(Long id) {
		reviewRepository.deleteById(id);
		return "Deleted Successfully!";
	}
		
}
