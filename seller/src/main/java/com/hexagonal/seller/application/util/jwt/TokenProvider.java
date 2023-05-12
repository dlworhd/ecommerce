package com.hexagonal.seller.application.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

	@Value("${jwt.secret}")
	String secret;

	@Value("${jwt.expiration}")
	String expiration;


	public String createToken(String email) {

		Date now = new Date(System.currentTimeMillis());

		return Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(now.getTime() + Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public String breakToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
		Date now = new Date(System.currentTimeMillis());

		return Jwts.builder().setExpiration(new Date(now.getTime() - 1000))
				.setSubject(claims.getSubject())
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secret).build()
				.parseClaimsJws(token).getBody();
	}


	public String getnameFromToken(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

	public boolean isValid(String token) {
		Date now = new Date();
		Date exp = getClaims(token).getExpiration();
		return exp.after(now);
	}
//
//	public Authentication getUserAuthentication(String token) {
//		String email = getnameFromToken(token);
//		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
//		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	}

}
