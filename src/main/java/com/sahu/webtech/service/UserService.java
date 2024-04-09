package com.sahu.webtech.service;

import com.sahu.webtech.dto.request.UserDTO;
import com.sahu.webtech.model.User;
import com.sahu.webtech.security.CustomOAuth2UserDTO;

public interface UserService {

	User findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	User registerUser(UserDTO user);
	
	void oAuthPostLoginProcess(CustomOAuth2UserDTO customOAuth2UserDTO);
	
}
