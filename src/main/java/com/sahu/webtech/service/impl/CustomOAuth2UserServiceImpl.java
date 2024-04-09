package com.sahu.webtech.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.sahu.webtech.model.Role;
import com.sahu.webtech.model.User;
import com.sahu.webtech.repository.UserRepository;
import com.sahu.webtech.security.CustomOAuth2UserDTO;

@Service
public class CustomOAuth2UserServiceImpl extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomOAuth2UserDTO loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		String email = oAuth2User.getAttribute("email");
		Optional<User> optUser = userRepository.findByEmail(email);
		List<String> userRoles = null;
		List<SimpleGrantedAuthority> authorities = null;
		User user = null;
		if (optUser.isPresent()) {
			user = optUser.get();
			userRoles = user.getRoles().stream().map(Role::getName).toList();
			authorities = userRoles.stream().map(SimpleGrantedAuthority::new).toList();
		}

		return new CustomOAuth2UserDTO(oAuth2User, email, userRoles, authorities);
	}

}
