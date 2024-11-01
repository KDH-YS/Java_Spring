package com.example.demo.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class CustomFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
    	
    	log.info("로그인 실패: " + exception.getMessage());
        // 실패 원인에 따라 메시지를 설정
        String errorMessage = "로그인에 실패했습니다.";

        if (exception.getMessage().contains("Bad credentials")) {
            errorMessage = "잘못된 아이디 또는 비밀번호입니다.";
        } else if (exception.getMessage().contains("User account is locked")) {
            errorMessage = "계정이 잠겨 있습니다. 관리자에게 문의하세요.";
        } else if (exception.getMessage().contains("User account is disabled")) {
            errorMessage = "비활성화된 계정입니다. 관리자에게 문의하세요.";
        } else if (exception.getMessage().contains("User account has expired")) {
            errorMessage = "계정이 만료되었습니다. 관리자에게 문의하세요.";
        }

        // URL 인코딩을 적용하여 한글 메시지를 쿼리 파라미터로 전달
        String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        response.sendRedirect("/login?error=true&exception=" + encodedMessage);
    }
}
