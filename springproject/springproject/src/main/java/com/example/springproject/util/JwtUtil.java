package com.example.springproject.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 用來簽名的 Secret Key（目前方法最不安全；從環境變數讀取相對最安全；從application.properties讀取則有被上傳到Git的風險）
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 建立 JWT
    public String generateToken(String phoneNumber) {
        long expirationMs = 1000 * 60 * 60 * 2; // 2 小時
        return Jwts.builder()
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    // 解析 Token 取得 Phone
    public String extractPhone(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
