package com.example.faq_backend_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.Date;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.faq_backend_api.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;

public class JwtServiceTest {
    private JwtService jwtService;
    private SecretKey mockSecretKey;

    @BeforeEach
    void setUp() throws Exception {
        jwtService = new JwtService();
        
     
        mockSecretKey = Jwts.SIG.HS256.key().build();

        Field secretKeyField = JwtService.class.getDeclaredField("SECRET_KEY");
        secretKeyField.setAccessible(true);
        secretKeyField.set(jwtService, mockSecretKey);
    }

    @Test
    void testGenerateToken() {
        String username = "testuser";
        String token = jwtService.generateToken(username);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testExtractUsername() {
        String username = "testuser";
        String token = jwtService.generateToken(username);

        assertEquals(username, jwtService.extractUsername(token));
    }

    @Test
    void testTokenNotExpired() {
        String token = jwtService.generateToken("testuser");

        assertFalse(jwtService.isTokenExpired(token));
    }

    @Test
    void testValidateToken() {
        String username = "testuser";
        String token = jwtService.generateToken(username);

        assertTrue(jwtService.validateToken(token, username));
    }

    @Test
    void testExtractClaims() {
        String username = "testuser";
        String token = jwtService.generateToken(username);

        Claims claims = jwtService.extractClaims(token);

        assertNotNull(claims);
        assertEquals(username, claims.getSubject());
        assertTrue(claims.getExpiration().after(new Date()));
    }

    @Test
    void testTokenIsExpired() {
        // Token mit kurzer Lebensdauer (1ms), damit es sofort abläuft
        String expiredToken = Jwts.builder()
                .subject("expiredUser")
                .expiration(new Date(System.currentTimeMillis() - 1000)) // in der Vergangenheit
                .signWith(mockSecretKey)
                .compact();

        assertTrue(jwtService.isTokenExpired(expiredToken));
    }

    @Test
    void testInvalidTokenThrowsException() {
        String invalidToken = "invalid.token.here";

        Exception exception = assertThrows(SignatureException.class, () -> {
            jwtService.extractClaims(invalidToken);
        });

        assertTrue(exception.getMessage().contains("JWT signature does not match"));
    }

    @Test
    void testExpiredTokenThrowsException() {
        String expiredToken = Jwts.builder()
                .subject("expiredUser")
                .expiration(new Date(System.currentTimeMillis() - 1000)) // Sofort abgelaufen
                .signWith(mockSecretKey)
                .compact();

        assertThrows(ExpiredJwtException.class, () -> {
            jwtService.extractClaims(expiredToken);
        });
    }
    
}
