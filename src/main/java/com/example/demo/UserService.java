package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public User getusername(String username) {
		
		return userMapper.getusername(username);
		
	}
	
	public void signup(String username, String pswd) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String hashedPassword = passwordEncoder.encode(pswd); // 비밀번호 해싱

	    User user = new User();
	    user.setUsername(username);
	    user.setPswd(hashedPassword); // 해싱된 비밀번호 저장
	    
	    userMapper.signup(user);
	}

	
	
}
