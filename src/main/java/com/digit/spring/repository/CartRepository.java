package com.digit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digit.spring.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findByUid(Long uid);
}
