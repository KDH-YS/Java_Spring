package com.example.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.example.demo.domain.User;

@Mapper
public interface DemoMapper {
	User findUser(String username);
	void signup(User user);
	void UserRoleMapping();
	void newrole();
	List<User> findAll();
}
