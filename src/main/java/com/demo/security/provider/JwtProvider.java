package com.demo.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.demo.security.authentication.JwtAuthentication;

public class JwtProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		if(authentication.equals(JwtAuthentication.class)) {
			return true;
		} else {
			return false;
		}
	}
	
}
