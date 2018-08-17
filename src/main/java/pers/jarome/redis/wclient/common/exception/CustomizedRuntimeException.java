package pers.jarome.redis.wclient.common.exception;

/**
 * 自定义的运行异常
 *
 * @author jarome
 * @date 2017/12/22
 **/
public class CustomizedRuntimeException extends RuntimeException {

    public CustomizedRuntimeException() {
    }

    public CustomizedRuntimeException(String message) {
        super(message);
    }

    public CustomizedRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizedRuntimeException(Throwable cause) {
        super(cause);
    }


}
