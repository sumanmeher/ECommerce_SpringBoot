package com.digit.spring.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
	Long rid;
	String text;
	Long uid;
	Long pid;
	@Override
	public String toString() {
		return "ReviewDTO [rid=" + rid + ", text=" + text + ", uid=" + uid + ", pid=" + pid + "]";
	}
	
	
	
}
