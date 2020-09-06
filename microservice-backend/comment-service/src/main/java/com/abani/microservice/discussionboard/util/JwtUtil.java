package com.abani.microservice.discussionboard.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.auth.secretKey}")
    private String secret_key;

    public String extractUsername(String token) {
        final Claims claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
        Function<Claims, String> claimsResolver = claim -> claim.getSubject();
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
        Function<Claims, Date> claimsResolver = claim -> claim.getExpiration();
        return claimsResolver.apply(claims);
    }
}