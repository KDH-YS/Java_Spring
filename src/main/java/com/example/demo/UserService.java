package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public User getusername(String username) {
        return userMapper.getusername(username);
    }
    
    // 회원가입 시 비밀번호를 암호화하여 저장
    public void signup(String username, String pswd) {
    	User user = new User();
    	
    	user.setUsername(username);
    	user.setPswd(pswd);
    }

    // 로그인 시 비밀번호 비교
//    public boolean checkPassword(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword); // 비밀번호 비교
//    }
}

