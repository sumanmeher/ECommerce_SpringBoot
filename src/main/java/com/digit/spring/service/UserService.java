package com.digit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digit.spring.entity.Products;
import com.digit.spring.entity.User;
import com.digit.spring.payload.ProductDTO;
import com.digit.spring.payload.UserDTO;
import com.digit.spring.repository.ProductRepository;
import com.digit.spring.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
//	@Autowired
//	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
//	@Autowired
//	private WishlistRepository wishlistRepository;
//	
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
	
//    private CartDTO EntityToDtoCart(Cart cart) {
//        CartDTO cartDto = new CartDTO();
//        cartDto.setId(cart.getId());
//        cartDto.setUid(cart.getUid());
//        cartDto.setPid(cart.getPid());
//        return cartDto;
//    }
//
//    private Cart dtoToEntityCart(CartDTO cartDto) {
//        Cart cart = new Cart();
//        cart.setId(cartDto.getId());
//        cart.setUid(cartDto.getUid());
//        cart.setPid(cartDto.getPid());
//        return cart;
//    }
    private Products dtoToEntityProduct(ProductDTO productDTO) {
		Products product = new Products();
		product.setPid(productDTO.getPid());
		product.setTitle(productDTO.getTitle());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
//		product.setUid(productDTO.getUid());
		return product;
	}

	private ProductDTO entityToDtoProduct(Products product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setPid(product.getPid());
		productDTO.setTitle(product.getTitle());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
//		productDTO.setUid(product.getUid());
		return productDTO;
	}
	
//	private WishlistDTO EntityToDtoWishlist(Wishlist wishlist) {
//		WishlistDTO wishlistDto = new WishlistDTO();
//		wishlistDto.setWid(wishlist.getWid());
//		wishlistDto.setUid(wishlist.getUid());
//		wishlistDto.setPid(wishlist.getPid());
//        return wishlistDto;
//    }
//
//    private Wishlist dtoToEntityWishlist(WishlistDTO wishlistDto) {
//    	Wishlist wishlist = new Wishlist();
//    	wishlist.setWid(wishlistDto.getWid());
//    	wishlist.setUid(wishlistDto.getUid());
//    	wishlist.setPid(wishlistDto.getPid());
//        return wishlist;
//    }
    
    
    
    
	
	public UserDTO getOneUser(Long uid, UserDTO userDto) {
		User oneUser = userRepo.getReferenceById(uid);
		if(oneUser.getPassword().equals(userDto.getPassword())) {
			System.out.println(oneUser.getProducts());
			return EntityToDtoUser(oneUser);
		}
		return null;
	}

	public UserDTO addProduct(UserDTO userDto) {
		User user = dtoToEntityUser(userDto);
		User savedUser = userRepo.save(user);
		return EntityToDtoUser(savedUser);
		
	}
	
//	cart method
	public String addToCart(Long uid, Long pid) {
		
		User user = userRepo.getReferenceById(uid);
		Products prod = productRepository.getReferenceById(pid);
		List<Products> cartProducts = user.getCartProducts();
		cartProducts.add(prod);
		user.setCartProducts(cartProducts);
		User save = userRepo.save(user);
		
		return "added to cart"; 
	}
	
	public List<ProductDTO> getAllOneUserCart(Long uid){
		User user = userRepo.getReferenceById(uid);
		List<ProductDTO> finalList = new ArrayList<>();
		for (Products prod : user.getCartProducts()) {
			finalList.add(entityToDtoProduct(prod));
		}
		return finalList;
		
		
	}
	
	public String addToWishlist(Long uid, Long pid) {
		User user = userRepo.getReferenceById(uid);
		Products prod = productRepository.getReferenceById(pid);
		List<Products> wishListProducts = user.getWishListProducts();
		wishListProducts.add(prod);
		user.setWishListProducts(wishListProducts);
		User save = userRepo.save(user);
		
		return "added to WishList";
	}
	
	public List<ProductDTO> getAllOneUserWishlist(Long uid){
		
		User user = userRepo.getReferenceById(uid);
		List<ProductDTO> finalList = new ArrayList<>();
		for (Products prod : user.getWishListProducts()) {
			finalList.add(entityToDtoProduct(prod));
		}
		return finalList;
	}

	public String removeFromWishList(Long uid, Long pid) {
		User user = userRepo.getReferenceById(uid);
		Products prod = productRepository.getReferenceById(pid);
		List<Products> wishListProducts = user.getWishListProducts();
		wishListProducts.remove(prod);
		user.setWishListProducts(wishListProducts);
		User save = userRepo.save(user);
		return "Removed Successfully";
	}

	public String removeFromCart(Long uid, Long pid) {
		User user = userRepo.getReferenceById(uid);
		Products prod = productRepository.getReferenceById(pid);
		List<Products> cartProducts = user.getCartProducts();
		cartProducts.remove(prod);
		user.setCartProducts(cartProducts); 
		User save = userRepo.save(user);
		return "Removed Successfully";
	}
}
