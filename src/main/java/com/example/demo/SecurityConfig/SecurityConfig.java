package com.example.demo.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
        .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
        )
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/home", "/signup").permitAll()
                .anyRequest().authenticated()
        )
        .logout(logout -> logout
        		.logoutSuccessUrl("/")
        		.permitAll()
        		);
        
		return http.build();
	}
}
