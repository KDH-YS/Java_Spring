package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public User getuserid(String userid) {
		
		return userMapper.getuserid(userid);
		
	}
	
}
