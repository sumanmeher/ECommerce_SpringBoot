package com.digit.spring.payload;

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
