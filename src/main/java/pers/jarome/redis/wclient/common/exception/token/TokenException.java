package pers.jarome.redis.wclient.common.exception.token;

import pers.jarome.redis.wclient.common.exception.CustomizedRuntimeException;

/**
 * @author jarome
 * @date 2018/1/2
 **/
public class TokenException extends CustomizedRuntimeException {

    public TokenException() {
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }
}
