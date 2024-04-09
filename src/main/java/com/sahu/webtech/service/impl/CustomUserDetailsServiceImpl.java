package com.sahu.webtech.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sahu.webtech.model.Role;
import com.sahu.webtech.model.User;
import com.sahu.webtech.repository.UserRepository;
import com.sahu.webtech.security.CustomUserDetailsDTO;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetailsDTO loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Inside loadUserByUsername() method");
		log.info("username - {}", username);
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User is not exist"));
		log.info("User - {}", user);

		if (Boolean.FALSE.equals(user.getActive())) {
			log.debug("User is not active");
			throw new UsernameNotFoundException("User is not active, please contact admin!");
		} else {
			Long loggedInUserId = user.getId();
			List<String> userRoles = user.getRoles().stream().map(Role::getName).toList();
			List<SimpleGrantedAuthority> authorities = userRoles.stream().map(SimpleGrantedAuthority::new).toList();

			log.debug("User object created");
			return new CustomUserDetailsDTO(username, user.getPassword(), authorities, loggedInUserId,
					user.getDisplayName(), user.getEmail(), userRoles);
		}
	}

}
