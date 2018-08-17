package pers.jarome.redis.wclient.common.web.annotaion.anno;

import java.lang.annotation.*;

/**
 * 登录认证注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authentication {

    /**
     * true : 校验登录状态
     * false : 不校验
     *
     * @return
     */
    boolean check() default true;

}
