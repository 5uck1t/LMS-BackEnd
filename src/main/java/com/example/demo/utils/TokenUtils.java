package com.example.demo.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import com.example.demo.model.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@Component
public class TokenUtils {

    private final Key key;

    public TokenUtils() {
        this.key = generateRandomKey();
    }

    private Key generateRandomKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSha256");
            keyGen.init(256); // 256-bit key for HS256
            SecretKey secretKey = keyGen.generateKey();
            return secretKey;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate key", e);
        }
    }

    public Key getKey() {
        return key;
    }

    public Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .verifyWith((SecretKey) this.getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw e;
        }
        return claims;
    }

    public boolean isExpired(String token) {
        return this.getClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public String getUsername(String token) {
        return this.getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        boolean valid = true;
        if(this.getClaims(token) == null) {
            valid = false;
        }
        if(valid && this.isExpired(token)) {
            valid = false;
        }

        return valid;
    }

    public String generateToken(UserDetails userDetails) {
        HashMap<String, Object> payload = new HashMap<String, Object>();
        payload.put("sub", userDetails.getUsername());
        payload.put("authorities", userDetails.getAuthorities());

        if (userDetails instanceof UserDetailsImpl customUser) {
            payload.put("userId", customUser.getUlogovaniKorisnikId());
        }

        return Jwts.builder().claims(payload).expiration(new Date(System.currentTimeMillis() + 86_400_000)).signWith(this.getKey()).compact();
    }
}

