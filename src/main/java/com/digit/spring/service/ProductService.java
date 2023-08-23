package com.digit.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digit.spring.entity.Products;
import com.digit.spring.entity.User;
import com.digit.spring.payload.ProductDTO;

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
		return productDTO;
	}

	public ProductDTO getSpecificProduct(Long id) {
		Products product = productRepo.getReferenceById(id);
		return entityToDtoProduct(product);
	}

	public ProductDTO addProduct(ProductDTO productDto) {
		Products prod = dtoToEntityProduct(productDto);
		Products productdto = productRepo.save(prod);
		return entityToDtoProduct(productdto);

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
