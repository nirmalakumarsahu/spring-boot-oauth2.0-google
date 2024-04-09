package com.sahu.webtech.service;

import com.sahu.webtech.model.Role;

public interface RoleService {

	Role findByName(String name);

	Role findById(Long id);

}
