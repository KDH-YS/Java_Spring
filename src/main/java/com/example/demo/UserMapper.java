package com.example.demo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	User getuserid(String userid);

	
}