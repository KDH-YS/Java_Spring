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
	public String logincheck(@RequestParam("username") String id, @RequestParam("pswd") String pswd, Model model) {        
	    User check = userService.getusername(id);
	    
	    if (check != null && check.getUsername() != null && check.getPswd().equals(pswd)) {
	        return "loginsuccess";
	    } else {
	        model.addAttribute("errorMessage", "아이디와 비밀번호를 확인해주세요."); // 오류 메시지 추가
	        return "loginpage"; // loginpage로 돌아감
	    }
	}
	@PostMapping("/signup")
	public String signup(@RequestParam("username") String username, 
	                     @RequestParam("pswd") String pswd, 
	                     Model model) {
	    // 사용자 존재 여부 확인
	    User existingUser = userService.getusername(username);
	    if (existingUser != null) {
	        model.addAttribute("errorMessage", "이미 존재하는 사용자입니다."); // 사용자 존재 시 메시지
	        return "loginpage"; // 다시 로그인 페이지로 돌아감
	    }
	    
	    // 회원가입 처리
	    userService.signup(username, pswd); // 사용자 정보를 DB에 저장
	    
	    model.addAttribute("successMessage", "회원가입 성공! 로그인 해주세요."); // 성공 메시지 추가
	    return "loginpage"; // 회원가입 후 로그인 페이지로 돌아감
	}



}
