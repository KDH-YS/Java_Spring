package co.kr.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import co.kr.entity.User;
import co.kr.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {

	private final FavoriteMapper favoriteMapper;	
	@GetMapping({"/","/index"})
	public String home() {
		return "index";
	}
	@PostMapping("/favorite")
	public String favorite() {
		return "favorite";
	}
	@GetMapping("/login")
	public String login() {
		return"login";
	}
	@GetMapping("/signup")
	public String Signup() {
		return "signup";
	}
	@GetMapping("/admin")
	public String admin(Model model) {
		List<User> users = favoriteMapper.findAll();
		model.addAttribute("users", users);

		return "admin";
	}
	
}
