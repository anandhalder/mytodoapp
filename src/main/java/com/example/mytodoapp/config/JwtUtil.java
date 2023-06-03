package com.example.mytodoapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {

	private final Key secretKey = Keys.hmacShaKeyFor("sIoVC8OFOgmxbk9XRYtY2zMKXuYXBGL2d3x1IV37".getBytes(StandardCharsets.UTF_8));
	// Jwt Expiration in milliseconds
	private final long jwtExpirationInMillis = 60000L;

	private Claims parseToken(String token) {

		// Parse the JWT token and return the Claims
		return Jwts.parserBuilder()
						.setSigningKey(secretKey)
						.build()
						.parseClaimsJws(token)
						.getBody();
	}

	public String getUsernameFromToken(String token) {

		// Get the username from the JWT token and return it
		Claims claims = Jwts.parserBuilder()
						.setSigningKey(secretKey)
						.build()
						.parseClaimsJws(token)
						.getBody();

		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		
		try {
			return parseToken(token) != null;
		} catch (Exception e) {
			// An exception occurred, indicating the token is invalid
			return false;
		}
	}

	public Authentication getAuthentication(UserDetails userDetails, HttpServletRequest request) {

		// Create an Authentication object with the username and empty credentials
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
	}

	public String generateToken(UserDetails userDetails) {

		// Create a JWT token with the username and expiration date
		return Jwts.builder()
						.setSubject(userDetails.getUsername())
						.setExpiration(new java.util.Date(System.currentTimeMillis() + jwtExpirationInMillis))
						.signWith(secretKey)
						.compact();
	}
}

