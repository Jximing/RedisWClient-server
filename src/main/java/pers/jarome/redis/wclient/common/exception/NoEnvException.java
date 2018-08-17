package pers.jarome.redis.wclient.common.exception;

/**
 * 没有环境变量异常
 *
 * @author jarome
 * @date 2017/12/23
 **/
public class NoEnvException extends CustomizedRuntimeException {

    public NoEnvException() {
    }

    public NoEnvException(String message) {
        super(message);
    }
}
