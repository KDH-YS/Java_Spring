package com.example.demo.domain;

import java.util.List;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String username;
	private String password;
    private List<Role> roles;
}
