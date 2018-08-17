package pers.jarome.redis.wclient.common.exception;
/**
 * 
 * ClassWrongTypeException
 * @description 类型错误
 * @author jiangliuhong
 * @date 2018/8/15 19:20
 */
public class ClassWrongTypeException extends RuntimeException{

    public ClassWrongTypeException() {
        super();
    }

    public ClassWrongTypeException(String message) {
        super(message);
    }

    public ClassWrongTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassWrongTypeException(Throwable cause) {
        super(cause);
    }
}
