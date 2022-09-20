package com.demo.security.provider;

import java.util.ArrayList;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.demo.security.authentication.JwtAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtProvider implements AuthenticationProvider{
	
	@Value("${JWT_SECRET_KEY}")
	private String secretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = authentication.getName();
		log.info("token = " + token);
		try {
			Claims claims = Jwts.parser()
							.setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
							.parseClaimsJws(token)
							.getBody();
			String role = claims.get("ROLE").toString();
			role = new String("ROLE_" + role);
			var auth = new ArrayList<GrantedAuthority>();
			auth.add(new SimpleGrantedAuthority(role));
			return new JwtAuthentication(token, null, auth);
			
		}
		catch(Exception ex) {
			return new JwtAuthentication("tidak valid", null);
		}
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
