package co.kr.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
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
	    String errorMessage = "로그인 실패: ";

	    if (exception instanceof BadCredentialsException) {
	        errorMessage += "잘못된 사용자 이름이나 비밀번호입니다.";
	    } else if (exception instanceof LockedException) {
	        errorMessage += "계정이 잠겨있습니다.";
	    } else if (exception instanceof DisabledException) {
	        errorMessage += "계정이 비활성화되었습니다.";
	    } else {
	        errorMessage += "알 수 없는 오류가 발생했습니다.";
	    }

	    request.getSession().setAttribute("errorMessage", errorMessage);
	    response.sendRedirect("/login?error=true");
	}

}
