package pers.jarome.redis.wclient.common.util;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import pers.jarome.redis.wclient.common.exception.token.ExpiredTokenException;
import pers.jarome.redis.wclient.common.exception.token.NoTokenException;

/**
 * 认证工具类
 *
 * @author jarome
 * @date 2018/1/2
 **/
public class AuthUtils {

    /**
     * 生成token
     *
     * @param username        用户昵称
     * @param tokenPeriodTime token有效时间(单位:毫秒)
     * @return
     */
    public static String createToken(String username, long tokenPeriodTime) {
        return JwtUtils.createJWT(username, tokenPeriodTime);
    }

    /**
     * 解析token
     *
     * @param token 登录时返回的字符串
     * @return username
     * @throws pers.jarome.redis.wclient.common.exception.token.NoTokenException      没有token异常
     * @throws pers.jarome.redis.wclient.common.exception.token.ExpiredTokenException token过期异常
     */
    public static String resolveToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new NoTokenException();
        }
        try {
            return JwtUtils.resolveJWT(token);
        } catch (ExpiredJwtException expiredJwtException) {
            throw new ExpiredTokenException();
        }
    }
}
