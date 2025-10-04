package com.authservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "AJJDHRE3DDJFB334DFBEKBJKBE86JEBREGU4835798JHKDHUIFJJBKDJB8458DJFJKJDBVK";

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
//        claims.put("email", "satish@gmail.com");
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .claims(claims)
//                .signWith(getKey(), SignatureAlgorithm.HS256)
                .signWith(getKey())
                .compact();
    }
    private Key getKey() {
        byte[] decode = Base64.getDecoder().decode(SECRET_KEY);
        int i = decode.length * 8;
//        return Keys.hmacShaKeyFor(decode);
//        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(decode);
        /* Based on Length of this decode -> Signing Algorithm is overloaded.
        If length is greater than 512 then it will be HmacSHA512.
        If length is greater than 256 then it will be HmacSHA256.
        If length is greater than 384 then it will be HmacSHA384.
        */
    }
    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build().parseSignedClaims(token)
                .getPayload();
    }

    public Date extractExpiration(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }


}
