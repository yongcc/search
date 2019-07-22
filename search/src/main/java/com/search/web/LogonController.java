package com.search.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogonController {

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
