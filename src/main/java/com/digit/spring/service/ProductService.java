package com.digit.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digit.spring.entity.Products;
import com.digit.spring.entity.Reviews;
import com.digit.spring.payload.ProductDTO;
import com.digit.spring.payload.ReviewDTO;
import com.digit.spring.repository.ProductRepository;
import com.digit.spring.repository.UserRepository;

@Service
public class ProductService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepo;

	private Products dtoToEntityProduct(ProductDTO productDTO) {
		Products product = new Products();
		product.setPid(productDTO.getPid());
		product.setTitle(productDTO.getTitle());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setUser(userRepository.getReferenceById(productDTO.getUid()));
		return product;
	}

	private ProductDTO entityToDtoProduct(Products product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setPid(product.getPid());
		productDTO.setTitle(product.getTitle());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setUid(product.getUser().getUid());
//		productDTO.setReviews(product.getReviews());
		return productDTO;
	}

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

	public TreeMap getSpecificProduct(Long id) {
		Products product = productRepo.getReferenceById(id);

		TreeMap treeMap = new TreeMap();

		treeMap.put("product", entityToDtoProduct(product));

		List<ReviewDTO> reviewList = new ArrayList();

		List<Reviews> reviews = product.getReviews();
		for (Reviews reviewObj : product.getReviews()) {
			reviewList.add(EntityToDtoReview(reviewObj));
		}
		treeMap.put("reviews", reviewList);

		return treeMap;
	}

	public String addProduct(ProductDTO productDto) {
		if (productDto.getTitle() != null && productDto.getPrice() != 0 && productDto.getDescription() != null
			&& productDto.getTitle()!="" && productDto.getDescription()!=""
				) {

			Products prod = dtoToEntityProduct(productDto);
			Products productdto = productRepo.save(prod);
			return "product added";
		} else {
			if(productDto.getTitle()==null || productDto.getTitle()=="") {
				return "title is empty";
			}else if(productDto.getPrice()==0) {
				return "price can not be zero";
			}else if(productDto.getDescription()==null || productDto.getDescription()=="") {
				return "description is empty";
			}else {
				return "something went wrong";
			}
			
		}
			

	}

	public List<ProductDTO> getAllProducts() {
		List<Products> prod = productRepo.findAll();
		return prod.stream().map(prods -> entityToDtoProduct(prods)).collect(Collectors.toList());
	}

	public ProductDTO updateProduct(Long id, ProductDTO productDto) {
		Products product = productRepo.getReferenceById(id);
		if (productDto.getTitle() != null)
			product.setTitle(productDto.getTitle());
		if (productDto.getPrice() != 0)
			product.setPrice(productDto.getPrice());
		if (productDto.getDescription() != null)
			product.setDescription(productDto.getDescription());

		Products save = productRepo.save(product);
		return entityToDtoProduct(save);
	}

	public String deleteProduct(Long id) {
		productRepo.deleteById(id);
		return "Deleted Successfully!";

	}

}
