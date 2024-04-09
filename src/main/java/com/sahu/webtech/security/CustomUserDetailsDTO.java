package com.sahu.webtech.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class CustomUserDetailsDTO extends User {

	private static final long serialVersionUID = 1L;

	private Long userId;
	private String displayName;
	private String email;
	private List<String> userRoles;
	
	public CustomUserDetailsDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUserDetailsDTO(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Long userId, String displayName, String email, List<String> userRoles) {
		super(username, password, authorities);
		this.userId = userId;
		this.displayName = displayName;
		this.email = email;
		this.userRoles = userRoles;
	}

}
