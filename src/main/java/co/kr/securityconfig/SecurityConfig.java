package co.kr.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import co.kr.handler.CustomFailureHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
    private CustomFailureHandler customFailureHandler;

	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
        .csrf(csrf -> csrf.disable())
        .formLogin(formLogin -> formLogin
                .loginPage("/login") 
                .failureHandler(customFailureHandler)
                .defaultSuccessUrl("/", true)
                .permitAll()
        )
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/home", "/signup","/favorite/**","/css/*","/js/*","/favorite/add").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        )
        .logout(logout -> logout
        		.logoutSuccessUrl("/")
        		.permitAll()
        		);
        
		return http.build();
	}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
