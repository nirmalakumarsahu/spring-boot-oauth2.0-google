package com.sahu.webtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sahu.webtech.constant.CommonConstants;

@Controller
@RequestMapping("/client/user")
public class UserController {

	@GetMapping("/dashboard")
	public String showDashBoardPage() {
		return CommonConstants.DASHBOARD_PAGE;
	}

}
