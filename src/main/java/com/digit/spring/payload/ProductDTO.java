package com.digit.spring.payload;

import com.digit.spring.entity.User;

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
}
