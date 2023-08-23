package com.digit.spring.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long pid;
 
	@Column(name = "title", nullable = false)
	String title;

	@Column(name = "price", nullable = false)
	int price;

	@Column(name = "description", nullable = false)
	String description;

	@ManyToOne 
	User user; 
	
	@OneToMany(mappedBy="product")
	private List<Reviews> reviews;
	
	@ManyToMany(mappedBy = "cartProducts", fetch = FetchType.LAZY)
	private List<User> cartUser;
	
	@ManyToMany(mappedBy = "wishListProducts", fetch = FetchType.LAZY)
	private List<User> wishListUser;
	
	
	
	
	


}
