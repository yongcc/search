package com.search.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.entity.User;
import com.search.model.Result;
import com.search.model.UserInfo;
import com.search.service.JwtService;
import com.search.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@PostMapping(value = "/signin")
	public Result signin(String id, String password, HttpServletResponse response) {
		User user = userService.getUser(id, password);
		String token = jwtService.create("id", id, "");
		response.setHeader(HttpHeaders.AUTHORIZATION, token);
		
		UserInfo info = new UserInfo();
		info.setId(user.getId());
		
		Result result = Result.successInstance();
		result.setData(info);
		return result;
	}

	@RequestMapping("/home")
	public String home() {
		return "GOOOOOOOOOOOOOOOD";
	}

	@RequestMapping("/login")
	public String login() {
		return "LLLLLOOGIN";
	}

	@RequestMapping("/me")
	public String me() {
		return "MMMMMMMMME";
	}
}
