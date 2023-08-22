package com.digit.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digit.spring.payload.ProductDTO;
import com.digit.spring.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/{pid}")
	public ProductDTO getOneProduct(@PathVariable Long pid) {
		return productService.getSpecificProduct(pid); 
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDto) {
		return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();

	}

}
