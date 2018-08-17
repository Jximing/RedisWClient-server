package pers.jarome.redis.wclient.common.web.encrypt.anno;

import pers.jarome.redis.wclient.common.web.encrypt.constants.EncryptMethod;

import java.lang.annotation.*;

/**
 * 加密参数解析注解
 * <p>
 * 放置在方法体上，加密返回值
 * </p>
 * <p>
 * 放置在参数前，解密参数，仅限于body请求体
 * </p>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptBody {

    /**
     * 加密方式
     * <p>
     * 在处理请求时按照该方式进行解密
     * </p>
     * <p>
     * 在组装返回值时按照该方式进行加密
     * </p>
     *
     * @return
     */
    EncryptMethod method() default EncryptMethod.AES;

}
