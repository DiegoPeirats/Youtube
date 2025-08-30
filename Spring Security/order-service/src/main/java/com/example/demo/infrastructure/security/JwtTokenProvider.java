package com.example.demo.infrastructure.security;

import java.security.Key;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    
    private Key key() {
    	
    	byte[] bytes = Decoders.BASE64.decode(jwtSecret);
    	return Keys.hmacShaKeyFor(bytes);
    	
    }

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getUsername(String token) {
		
		return claims(token).getSubject();
	}

	public Long getUserId(String token) {
		
		return Long.parseLong(claims(token).get("id").toString());
	}

	public List<GrantedAuthority> getAuthorities(String token) {
	    Claims claims = claims(token);

	    String role = claims.get("role", String.class);

	    if (role != null) {
	        return List.of(new SimpleGrantedAuthority(role));
	    }

	    return List.of();
	}
	
	private Claims claims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

}
