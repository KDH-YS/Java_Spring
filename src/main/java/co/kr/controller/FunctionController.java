package co.kr.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.kr.entity.User;
import co.kr.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class FunctionController {
	private final PasswordEncoder passwordEncoder;
	private final FavoriteMapper favoriteMapper;
	
	@PostMapping("/signup")
	public String signup(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));

		
		favoriteMapper.signup(user);
		favoriteMapper.UserRoleMapping();
		favoriteMapper.newrole();
		
		return "redirect:/";
	}
	
}
