package com.sahu.webtech.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahu.webtech.constant.Provider;
import com.sahu.webtech.constant.Status;
import com.sahu.webtech.dto.request.UserDTO;
import com.sahu.webtech.model.Role;
import com.sahu.webtech.model.User;
import com.sahu.webtech.repository.RoleRepository;
import com.sahu.webtech.repository.UserRepository;
import com.sahu.webtech.security.CustomOAuth2UserDTO;
import com.sahu.webtech.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User registerUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		user.setUuid(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setDisplayName(userDTO.getFirstName() + " " + userDTO.getLastName());
		user.setProvider(Provider.LOCAL);
		user.setStatus(Status.ACTIVE);
		user.setActive(true);

		Optional<Role> role = roleRepository.findByName("USER");
		if (role.isPresent()) {
			user.setRoles(Set.of(role.get()));
		}

		return userRepository.save(user);
	}

	@Override
	public void oAuthPostLoginProcess(CustomOAuth2UserDTO customOAuth2UserDTO) {
		User user = new User();
		user.setUuid(UUID.randomUUID().toString());
		user.setFirstName(customOAuth2UserDTO.getName());
		user.setEmail(customOAuth2UserDTO.getEmail());
		user.setProvider(Provider.GOOGLE);
		user.setStatus(Status.ACTIVE);
		user.setActive(true);

		Optional<Role> role = roleRepository.findByName("USER");
		if (role.isPresent()) {
			user.setRoles(Set.of(role.get()));
		}

		userRepository.save(user);
	}

}
