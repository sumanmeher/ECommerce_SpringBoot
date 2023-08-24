package com.digit.spring.payload;

import java.util.List;

import com.digit.spring.entity.Reviews;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	Long pid;
	String title;
	int price;
	String description;
	Long uid;	
	
//	List<Reviews> reviews;
}
