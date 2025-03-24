package com.example.faq_backend_api.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    private final Key SECRET_KEY = generateSecretKey();

    private Key generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256); 
            return keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Fehler bei der Schlüsselerzeugung", e);
        }
    }


    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
                .signWith(SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        JwtParser parser = Jwts.parser() 
                .verifyWith((SecretKey) SECRET_KEY)        
                .build(); 
        return parser.parseSignedClaims(token).getPayload();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
