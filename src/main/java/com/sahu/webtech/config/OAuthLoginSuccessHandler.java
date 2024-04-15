package com.sahu.webtech.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sahu.webtech.model.User;
import com.sahu.webtech.security.CustomOAuth2UserDTO;
import com.sahu.webtech.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger LOGGER = LoggerFactory.getLogger(OAuthLoginSuccessHandler.class);

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		LOGGER.debug("Inside onAuthenticationSuccess() method");
		CustomOAuth2UserDTO oauth2User = (CustomOAuth2UserDTO) authentication.getPrincipal();
		User optUser = userService.findByEmail(oauth2User.getEmail());
		if (optUser == null) {
			userService.oAuthPostLoginProcess(oauth2User);
		}

		response.sendRedirect("/client/user/dashboard");
	}

}
