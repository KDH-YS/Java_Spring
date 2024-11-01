package co.kr.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.kr.entity.User;
import co.kr.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {
	
	private final FavoriteMapper favoriteMapper;
	private final PasswordEncoder passwordEncoder; // PasswordEncoder 주입 추가

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = favoriteMapper.findUser(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		
		// 권한 설정
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole())) 
				.collect(Collectors.toList());

		// UserDetails 객체 반환
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), // 이 부분은 rawPassword가 아닌 저장된 암호화된 비밀번호입니다.
				true, true, true, true, authorities);
	}

	// 로그인 검증 메서드 추가
	public void validatePassword(String rawPassword, User user) {
		if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
	}
}
