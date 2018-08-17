package pers.jarome.redis.wclient.common.util;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * jwt工具类
 *
 * @author jarome
 * @date 2017/12/22
 **/
public class JwtUtils {

    private final static String JWT_KEY = "redis-wclient$#&!><?SD*o349()?/,<>+";

    private final static String JWT_ID = "pers.jarome.redis.wclient";

    private final static String JWT_ISSUE = "redis-weclient-admin";

    /**
     * @param subject   所要传输的数据
     * @param ttlMillis 生命周期(单位:毫秒)
     * @return
     */
    public static String createJWT(String subject, long ttlMillis) {
        return createJWT(JWT_ID, JWT_ISSUE, subject, ttlMillis);
    }

    /**
     * @param id        密钥 ID
     * @param issuer    jwt签发者
     * @param subject   所要传输的数据
     * @param ttlMillis token生命周期(单位:毫秒)
     * @return 加密后JWT token字符串
     */
    private static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    /**
     * 解析jwt
     *
     * @param jwt
     * @return subject
     */
    public static String resolveJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
