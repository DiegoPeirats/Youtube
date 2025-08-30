package com.example.demo.infrastructure.security;

import java.time.LocalDate;
import java.security.Key;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.domain.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
	
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    
    @Value("${app.jwt-expiration}")
    private long jwtExpirationDate;
    
    private final UserRepository repository;
    
    public String generateToken(Authentication authentication) {
    	
    	String username = authentication.getName();
    	Date currentDate = Date.valueOf(LocalDate.now());
    	Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);
    	
    	User userFound = repository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException());
    	
    	return Jwts.builder()
    			.setSubject(username)
                .claim("id", userFound.getId())          
                .claim("role", userFound.getRole().name())
    			.setIssuedAt(currentDate)
    			.setExpiration(expirationDate)
    			.signWith(key())
    			.compact();
    }
    
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
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
	
	

}
