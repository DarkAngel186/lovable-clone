package com.lp.projects.lovable_clone.security;

import com.lp.projects.lovable_clone.dto.security.JwtUserPrincipal;
import com.lp.projects.lovable_clone.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getName())
                .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 10 mins expiratio
                .signWith(getSecretKey())
                .compact();
    }

    public JwtUserPrincipal validateToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Long id = Long.parseLong(claims.get("userId", String.class));
        String username = claims.getSubject();

        return new JwtUserPrincipal(id, username, new ArrayList<>());
    }

    public Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof JwtUserPrincipal userPrincipal)) {
            throw new RuntimeException("User not authenticated");
        }

        return userPrincipal.id();
    }
}
