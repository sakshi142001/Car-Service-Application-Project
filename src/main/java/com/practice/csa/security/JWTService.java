package com.practice.csa.security;

import java.security.Key;
import java.sql.Date;
import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.subst.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JWTService {
	@Value("${app.jwt.secret}")
	private String secret;

	public String createJWT(String username, String userRole, Duration duration) {

		return Jwts.builder().setClaims(Map.of("userRole", userRole, "username", username))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + duration.getSeconds() * 1000))
				.signWith(signInKey(),SignatureAlgorithm.HS256).compact();

	}

	Key signInKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}
	
	public Claims parseJWT(String token){
		return Jwts.parserBuilder().setSigningKey(signInKey()).build().parseClaimsJws(token).getBody();
	}
     
	
}