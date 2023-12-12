package com.viz.visualdataboard.security;

import com.viz.visualdataboard.domain.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Cookie createUserToken(String username, Role role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1시간

        String token = Jwts.builder()
                .setSubject(username)  // 토큰의 주인
                .claim("role", role.getRoleName())   // 토큰에 담을 추가 정보
                .setIssuedAt(now)      // 토큰 발행 시간
                .setExpiration(validity) // 토큰 만료 시간
                .signWith(secretKey)   // 사용할 서명 알고리즘과 서명 키
                .compact();

        Cookie cookie = new Cookie("token", token);  // 쿠키 생성
        cookie.setHttpOnly(true);  // 쿠키를 HTTP-only로 설정
        cookie.setMaxAge(60 * 60);  // 쿠키의 유효기간을 1시간으로 설정
        cookie.setPath("/");  // 쿠키의 경로를 설정

        return cookie;  // 쿠키를 응답에 추가
    }

}


