package cn.edu.zucc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET = "product_sec_kill";
    private static final Long EXPIRATION = 3600L;
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    private static final JWTVerifier VERIFIER = JWT.require(ALGORITHM).build();
    private static final Map<String, Object> HEADER = new HashMap<>();

    public static String renameToken(String token) {
        if (token != null && token.length() > 7) {
            return token.substring(7);
        }
        return null;
    }

    public static String createToken(Long userId, String account, String password) {
        HEADER.put("alg", "HS256");
        HEADER.put("typ", "JWT");
        return JWT.create()
                // 添加头部
                .withHeader(HEADER)
                // 放入用户的id
                .withAudience(String.valueOf(userId))
                // 将用户的账号和密码放入
                .withClaim("account", account)
                .withClaim("password", password)
                // 超时设置,设置过期的日期
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                // 签发时间
                .withIssuedAt(new Date())
                // SECRET加密
                .sign(ALGORITHM);
    }

    public static Integer getUserId(String token) {
        if (Strings.isBlank(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = VERIFIER.verify(token);
            if (null != jwt) {
                List<String> audience = jwt.getAudience();
                if (audience != null && audience.size() > 0) {
                    return Integer.parseInt(audience.get(0));
                }
            }
        } catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long getExpiration(String token) {
        if (Strings.isBlank(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = VERIFIER.verify(token);
            if (null != jwt) {
                return jwt.getExpiresAt().getTime();
            }
        } catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getAccount(String token) {
        if (Strings.isBlank(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = VERIFIER.verify(token);
            if (null != jwt) {
                return jwt.getClaim("account").asString();
            }
        } catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPassword(String token) {
        if (Strings.isBlank(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = VERIFIER.verify(token);
            if (null != jwt) {
                return jwt.getClaim("password").asString();
            }
        } catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
