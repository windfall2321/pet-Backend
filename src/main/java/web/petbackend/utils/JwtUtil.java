package web.petbackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定密钥
     * @return
     */
    public static String createJWT(Integer userId) {
        // 指定签名的时候使用的签名算法，也就是header那部分

        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24小时过期
                .signWith(key)
                .compact();
    }



    /**
     * 解析JWT token
     * @param token JWT token
     * @return 用户ID
     */
    public static Integer parseToken(String token) {
        try {
            // 移除Bearer前缀（如果存在）
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            
            // 检查token是否过期
            if (claims.getExpiration().before(new Date())) {
                throw new RuntimeException("Token已过期");
            }
            
            // 返回用户ID
            return Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException("Token解析失败: " + e.getMessage());
        }
    }
    
    /**
     * 验证token是否有效
     * @param token JWT token
     * @return 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}