package com.example.demo;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
//	@GetMapping("/")
//	public String index() {
//		return"index";
//	}
	
	@GetMapping("/")
	public String login() {
		return "loginpage";
	}
	
	@PostMapping("/logincheck")
	public String logincheck(@RequestParam("userid") String id, @RequestParam("userpswd") String pswd, Model model) {		
	    User check = userService.getuserid(id);
	    
	    if (check != null && check.getUserid() != null && check.getPswd().equals(pswd)) {
	        return "loginsuccess";
	    } else {
	        model.addAttribute("errorMessage", "아이디와 비밀번호를 확인해주세요.");
	        return "loginpage"; // loginpage로 돌아감
	    }
	}


}
