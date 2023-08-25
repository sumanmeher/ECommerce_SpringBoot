package com.digit.spring.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digit.spring.entity.Reviews;
import com.digit.spring.payload.ProductDTO;
import com.digit.spring.payload.ReviewDTO;
import com.digit.spring.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{pid}")
	public ResponseEntity getOneProduct(@PathVariable Long pid) {
		return new ResponseEntity<>(productService.getSpecificProduct(pid), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDto) {
		return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
	}

	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();

	}

	@PutMapping("/{pid}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long pid, @RequestBody ProductDTO productDto) {
		return new ResponseEntity<>(productService.updateProduct(pid, productDto), HttpStatus.CREATED);
	}

	@DeleteMapping("/{pid}")
	public String deleteProduct(@PathVariable Long pid) {
		return productService.deleteProduct(pid);
	}

}
