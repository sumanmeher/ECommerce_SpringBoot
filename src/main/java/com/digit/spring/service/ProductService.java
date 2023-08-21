package com.digit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digit.spring.repository.ProductReository;

@Service
public class ProductService {
	@Autowired
	private ProductReository productRepo;
}
