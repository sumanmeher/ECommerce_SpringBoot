package com.digit.spring.payload;

import com.digit.spring.entity.User;

public class ProductDTO {
	Long pid;
	String title;
	int price;
	String description;
	Long uid;
//	User uid;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public User getUid() {
//		return uid;
//	}
//
//	public void setUid(User uid) {
//		this.uid = uid;
//	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}
