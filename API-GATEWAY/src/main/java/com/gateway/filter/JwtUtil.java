package com.gateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "AJJDHRE3DDJFB334DFBEKBJKBE86JEBREGU4835798JHKDHUIFJJBKDJB8458DJFJKJDBVK";

    private Key getKey() {
        byte[] decode = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

    public void validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build().parseSignedClaims(token);
    }
}
