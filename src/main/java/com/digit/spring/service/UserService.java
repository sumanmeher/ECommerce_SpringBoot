package com.digit.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digit.spring.entity.Cart;
import com.digit.spring.entity.Products;
import com.digit.spring.entity.User;
import com.digit.spring.payload.CartDTO;
import com.digit.spring.payload.ProductDTO;
import com.digit.spring.payload.UserDTO;
import com.digit.spring.repository.CartRepository;
import com.digit.spring.repository.ProductReository;
import com.digit.spring.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductReository productReository;
	
	private UserDTO EntityToDtoUser(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setUid(user.getUid());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

	private User dtoToEntityUser(UserDTO userDto) {
		User user = new User();
		user.setUid(userDto.getUid());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
    private CartDTO EntityToDtoCart(Cart cart) {
        CartDTO cartDto = new CartDTO();
        cartDto.setId(cart.getId());
        cartDto.setUid(cart.getUid());
        cartDto.setPid(cart.getPid());
        return cartDto;
    }

    private Cart dtoToEntityCart(CartDTO cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setUid(cartDto.getUid());
        cart.setPid(cartDto.getPid());
        return cart;
    }
    private Products dtoToEntityProduct(ProductDTO productDTO) {
		Products product = new Products();
		product.setPid(productDTO.getPid());
		product.setTitle(productDTO.getTitle());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setUid(productDTO.getUid());
		return product;
	}

	private ProductDTO entityToDtoProduct(Products product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setPid(product.getPid());
		productDTO.setTitle(product.getTitle());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setUid(product.getUid());
		return productDTO;
	}
	
	public UserDTO getOneUser(Long uid, UserDTO userDto) {
		User oneUser = userRepo.getReferenceById(uid);
		if(oneUser.getPassword().equals(userDto.getPassword()))
			return EntityToDtoUser(oneUser);
		return null;
	}

	public UserDTO addProduct(UserDTO userDto) {
		User user = dtoToEntityUser(userDto);
		User savedUser = userRepo.save(user);
		return EntityToDtoUser(savedUser);
		
	}
	
	//cart method
	public CartDTO addToCart(Long uid, Long pid) {
		CartDTO cartDto = new CartDTO();
		cartDto.setPid(pid);
		cartDto.setUid(uid);
		
		Cart cart = dtoToEntityCart(cartDto);
		Cart save = cartRepository.save(cart);
		return EntityToDtoCart(save);
	}
	
	public List<ProductDTO> getAllOneUserCart(Long uid){
		List<Cart> productList = cartRepository.findByUid(uid);
//		return productList.stream().map(prods -> EntityToDtoCart(prods)).collect(Collectors.toList());
		List<ProductDTO> finalList = new ArrayList<>();
		for (Cart cart : productList) {
			Long pId = cart.getPid();
			Products prod = productReository.getReferenceById(pId);
			finalList.add(entityToDtoProduct(prod));
		}
		return finalList;
		
		
	}
	
}
