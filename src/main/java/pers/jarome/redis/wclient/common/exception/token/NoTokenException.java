package pers.jarome.redis.wclient.common.exception.token;

/**
 * 无token异常
 *
 * @author jarome
 * @date 2018/1/2
 **/
public class NoTokenException extends TokenException {
    public NoTokenException() {
    }

    public NoTokenException(String message) {
        super(message);
    }
}
