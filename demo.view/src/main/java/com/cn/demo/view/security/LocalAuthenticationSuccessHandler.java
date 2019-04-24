package com.cn.demo.view.security;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.cn.demo.view.model.BaseUser;
import com.cn.demo.view.redis.SpringGloabUserRedisUtils;

@Component
public class LocalAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	@Resource
	private SpringGloabUserRedisUtils springGloabUserRedisUtils;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		BaseUser baseUser = (BaseUser) authentication.getPrincipal();
		
		springGloabUserRedisUtils.add(UUID.randomUUID().toString(), 5, baseUser);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("baseUser", baseUser);
		
		response.sendRedirect("/main"); 
		
	}


}
