package com.digit.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digit.spring.entity.Products;
import com.digit.spring.payload.ProductDTO;
import com.digit.spring.repository.ProductReository;

@Service
public class ProductService {
	@Autowired
	private ProductReository productRepo;
	
	private Products dtoToEntityProduct(ProductDTO productDTO) {
        Products product = new Products();
        product.setPid(productDTO.getPid());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setUid(productDTO.getUid());
        return product;
    }
	
	private ProductDTO entityToDto(Products product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPid(product.getPid());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setUid(product.getUid());
        return productDTO;
    }
	
	public ProductDTO getSpecificProduct(Long id) {
		Products product = productRepo.getReferenceById(id);
		return entityToDto(product);
	}
	
	public ProductDTO addProduct(ProductDTO productDto) {
		Products prod = dtoToEntityProduct(productDto);
		Products productdto = productRepo.save(prod);
		return entityToDto(productdto);
		
	}

	public List<ProductDTO> getAllProducts() {
		List<Products> prod = productRepo.findAll();
		return prod.stream().map(prods->entityToDto(prods)).collect(Collectors.toList());
	}
}
