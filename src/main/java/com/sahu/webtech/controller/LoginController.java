package com.sahu.webtech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sahu.webtech.constant.CommonConstants;
import com.sahu.webtech.dto.request.UserDTO;
import com.sahu.webtech.model.User;
import com.sahu.webtech.service.UserService;

@Controller
public class LoginController {

	private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

	@GetMapping({ "/", "/login" })
	public String showLoginPage() {
		return CommonConstants.LOGIN_PAGE;
	}

	@GetMapping("/registration")
	public String showRegistrationPage() {
		return CommonConstants.REGISTRATION_PAGE;
	}

	@PostMapping("/registration")
	public String registrationProcess(RedirectAttributes redirectAttributes,
			@ModelAttribute("userDTO") UserDTO userDTO) {
		LOGGER.debug("Inside registrationProcess() method");
		LOGGER.info("User data - " + userDTO.getEmail());
		if (userDTO.getEmail() != null) {
			Boolean existByEmail = userService.existsByEmail(userDTO.getEmail());

			if (existByEmail) {
				redirectAttributes.addFlashAttribute(CommonConstants.ERROR,
						environment.getProperty("duplicate_user_error_msg"));
				return CommonConstants.REDIRECT_REGISTRATION;
			} else {
				User registeredUserId = userService.registerUser(userDTO);
				if (registeredUserId != null) {
					redirectAttributes.addFlashAttribute(CommonConstants.SUCCESS,
							environment.getProperty("registration_success_msg"));
				} else {
					redirectAttributes.addFlashAttribute(CommonConstants.ERROR,
							environment.getProperty("registration_failed_msg"));
				}
			}
		}

		return CommonConstants.REDIRECT_LOGIN_PAGE;
	}

	@GetMapping("/forget-password")
	public String showForgetPasswordPage() {
		return CommonConstants.FORGET_PASSWORD_PAGE;
	}

}
