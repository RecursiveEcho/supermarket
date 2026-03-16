package Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    
    private static final String SECRET_STRING = "this-is-a-256-bit-secure-secret-key-for-jwt-token";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 小时

    /**
     * 生成 JWT Token
     */
    public static String generateJwt(Long userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", username);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 验证 JWT Token
     */
    public static boolean validateJwt(String jwt) {
        try {
            Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(jwt);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token 已过期");
        } catch (JwtException e) {
            System.out.println("Token 无效：" + e.getMessage());
        }
        return false;
    }
}
