package com.digit.spring.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	Long uid;
	
	@Column(name="name", nullable=false)
	String name;
	
	@Column(name="email", nullable=false)
	String email;
	
	@Column(name="password", nullable=false)
	String password;

	
	@OneToMany(mappedBy="user")
	private List<Products> products;
	
	@OneToMany(mappedBy="user")
	private List<Reviews> reviews;
	
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="cart",
	joinColumns = {
			@JoinColumn(name="uid", referencedColumnName = "uid")
	},
	inverseJoinColumns = {
			@JoinColumn(name="pid", referencedColumnName = "pid")
	})
	private List<Products> cartProducts;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="wishlist",
	joinColumns = {
			@JoinColumn(name="uid", referencedColumnName = "uid")
	},
	inverseJoinColumns = {
			@JoinColumn(name="pid", referencedColumnName = "pid")
	})
	private List<Products> wishListProducts;
	
	


	
}
