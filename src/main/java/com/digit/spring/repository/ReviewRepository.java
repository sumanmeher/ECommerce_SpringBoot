package com.digit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digit.spring.entity.Reviews;

public interface ReviewRepository extends JpaRepository<Reviews, Long>{

}
