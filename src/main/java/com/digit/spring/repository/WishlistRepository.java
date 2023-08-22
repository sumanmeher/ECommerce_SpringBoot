package com.digit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digit.spring.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>{
	List<Wishlist> findByUid(Long uid);

}
