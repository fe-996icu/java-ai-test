package com.icu;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// jjwt 0.12.6
public class JwtTest {
    // HS256 算法要求密钥的长度至少为 256 位（32 字节）
    private static final String SECRET = "YWRtaW4=========================================================================================================";

    @Test
    public void testGenerateJwt() {
        // 1. 定义密钥
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
        // 2. 定义 JWT 的 Claims（负载）
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "admin");

        // 3. 设置过期时间
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 10); // 10 分钟后过期

        // 4. 生成 JWT
        String jwt = Jwts.builder()
                .claims(claims) // 设置负载（Claims）自定义数据
                .expiration(expiration) // 设置过期时间
                .signWith(key) // 使用密钥签名
                .compact(); // 生成 JWT 字符串

        System.out.println("生成的 JWT 信息：\n" + jwt);
    }

    @Test
    public void testParseJwt() {
        // 1. 定义密钥（必须与生成 JWT 时使用的密钥一致）
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

        // 2. 要解析的 JWT
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTczNzk4MDkyNX0.n2Qm3tFRMVTm_FOcJ_Xmnw7cyvyWm16bAaquKiu5jHI8ZbtE56MvPc1i4iQhcmIRuB-d4FW3-ZCr3GTcYsxIeg"; // 替换为实际的 JWT

        // 3. 解析 JWT
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key) // 使用密钥验证签名
                    .build()
                    .parseSignedClaims(jwt) // 解析 JWT
                    .getPayload(); // 获取 Claims

            // 4. 输出解析结果
            System.out.println("解析后的 JWT 数据：\n" + claims);
        }catch (Exception e){
            System.out.println("解析 JWT 失败：" + e.getMessage());
        }

    }
}
