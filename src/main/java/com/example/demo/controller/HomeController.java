package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.User;
import com.example.demo.mappers.DemoMapper;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class HomeController {

	private final DemoMapper demoMapper;
	
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
	@GetMapping("/admin")
	public String admin(Model model) {
		List<User> users = demoMapper.findAll();
		model.addAttribute("users", users);

		return "admin";
	}
	
	
}
