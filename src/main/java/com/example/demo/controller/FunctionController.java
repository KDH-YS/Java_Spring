package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.User;
import com.example.demo.mappers.DemoMapper;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class FunctionController {
	private final PasswordEncoder passwordEncoder;
	private final DemoMapper demoMapper;
	
	@PostMapping("/signup")
	public String signup(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));

		
		demoMapper.signup(user);
		demoMapper.UserRoleMapping();
		demoMapper.newrole();
		
		return "redirect:/";
	}
	
}
