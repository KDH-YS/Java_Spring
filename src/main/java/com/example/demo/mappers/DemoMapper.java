package com.example.demo.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.User;

@Mapper
public interface DemoMapper {
	User findUser(String userid);
	
}
