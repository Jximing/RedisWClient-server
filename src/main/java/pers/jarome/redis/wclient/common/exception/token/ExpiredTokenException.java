package pers.jarome.redis.wclient.common.exception.token;

/**
 * token认证过期异常
 *
 * @author jarome
 * @date 2018/1/2
 **/
public class ExpiredTokenException extends TokenException {

    public ExpiredTokenException() {
    }

    public ExpiredTokenException(String message) {
        super(message);
    }

}
