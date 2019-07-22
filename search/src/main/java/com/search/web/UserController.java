package com.search.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.entity.User;
import com.search.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getAllUsers")
	public Iterable<User> getAllUsers() {
		return userService.getAllUsers();
	}
}
