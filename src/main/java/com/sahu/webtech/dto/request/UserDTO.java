package com.sahu.webtech.dto.request;

import lombok.Data;

@Data
public class UserDTO {
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
}
