package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping({"/","/index"})
	public String Home() {
		return "index";
	}
	@GetMapping("/login")
	public String Login() {
		return"login";
	}
	@GetMapping("/signup")
	public String Signup() {
		return "signup";
	}
	
	
}
