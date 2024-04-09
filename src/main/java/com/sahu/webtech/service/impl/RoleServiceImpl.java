
package com.sahu.webtech.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahu.webtech.model.Role;
import com.sahu.webtech.repository.RoleRepository;
import com.sahu.webtech.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		Optional<Role> optionalRole = roleRepository.findByName(name);
		return optionalRole.isPresent() ? optionalRole.get() : null;
	}

	@Override
	public Role findById(Long id) {
		Optional<Role> optionalRole = roleRepository.findById(id);
		return optionalRole.isPresent() ? optionalRole.get() : null;
	}

}
