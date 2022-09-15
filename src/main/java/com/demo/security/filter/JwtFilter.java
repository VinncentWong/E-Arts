package com.demo.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.security.authentication.JwtAuthentication;
import com.demo.security.manager.JwtManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter{
	
	private final JwtManager manager;
	
	@Autowired
	public JwtFilter(JwtManager manager) {
		this.manager = manager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if(header == null) {
			filterChain.doFilter(request, response);
			return;
		}
		JwtAuthentication jwtAuth = new JwtAuthentication(header, null);
		Authentication auth = manager.authenticate(jwtAuth);
		log.info("authenticated = " + auth.isAuthenticated());
		if(!auth.isAuthenticated()){
			response.sendError(HttpStatus.FORBIDDEN.value(), "header \"Authorization\" tidak valid!");
			return;
		} 
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
	}

}
