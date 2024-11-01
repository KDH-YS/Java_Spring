package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "loginpage";
    }

    @PostMapping("/logincheck")
    public String logincheck(@RequestParam("username") String id, @RequestParam("pswd") String pswd, Model model) {
        User check = userService.getusername(id);
        
        if (check != null && check.getUsername() != null) {
            return "loginsuccess";
        } else {
            model.addAttribute("errorMessage", "아이디와 비밀번호를 확인해주세요.");
            return "loginpage"; // 로그인 페이지로 돌아감
        }
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> signup(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        
        // 사용자 존재 여부 확인
        User existingUser = userService.getusername(user.getUsername());
        if (existingUser != null) {
            response.put("message", "이미 존재하는 사용자입니다.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT); // 409 Conflict 상태 반환
        }

        // 회원가입 처리
        userService.signup(user.getUsername(), user.getPswd());
        response.put("message", "회원가입 성공! 로그인 해주세요.");
        
        return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK 상태 반환
    }
}
