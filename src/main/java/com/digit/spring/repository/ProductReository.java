package com.digit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digit.spring.entity.Products;

public interface ProductReository extends JpaRepository<Products, Long> {

}
