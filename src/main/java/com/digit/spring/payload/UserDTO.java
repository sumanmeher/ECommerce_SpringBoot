package com.digit.spring.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	Long uid;
	String name;
	String email;
	String password;
}
