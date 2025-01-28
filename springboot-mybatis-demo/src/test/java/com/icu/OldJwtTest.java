package com.icu;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// jjwt 0.9.1
public class OldJwtTest {
    // HS256 算法要求密钥的长度至少为 256 位（32 字节）
    private static final String SECRET = "YWRtaW4=========================================================================================================";

    // @Test
    // public void testGenerateJwt() {
    //     Map<String, Object> dataMap = new HashMap<>();
    //     dataMap.put("id", 1);
    //     dataMap.put("username", "admin");
    //
    //     String jwt = Jwts.builder()
    //             .signWith(SignatureAlgorithm.HS256, SECRET) // 指定加密算法，秘钥
    //             .addClaims(dataMap) // 添加自定义信息
    //             .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
    //             .compact(); // 生成令牌
    //
    //     System.out.println("生成 Token 信息：\n" + jwt);
    // }

    // @Test
    // public void testParseJwt() {
    //     String token = "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTczNzk4MDkyNX0.n2Qm3tFRMVTm_FOcJ_Xmnw7cyvyWm16bAaquKiu5jHI8ZbtE56MvPc1i4iQhcmIRuB-d4FW3-ZCr3GTcYsxIeg"; // 替换为实际的 JWT
    //
    //     Claims claims = Jwts.parser()
    //             .setSigningKey(SECRET) // 指定密钥
    //             .parseClaimsJws(token) // 解析令牌
    //             .getBody(); // 获取自定义信息
    //
    //     System.out.println("解析后的 Token 数据：\n" + claims);
    // }
}
