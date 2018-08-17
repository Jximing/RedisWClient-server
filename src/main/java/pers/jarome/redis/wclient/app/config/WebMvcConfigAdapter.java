package pers.jarome.redis.wclient.app.config;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pers.jarome.redis.wclient.common.web.encrypt.method.handler.EncryptBodyRturnValueHandler;
import pers.jarome.redis.wclient.common.web.interceptor.AuthenticationInterceptor;
import pers.jarome.redis.wclient.common.web.encrypt.method.resolver.EncryptArgumentResolver;

import java.util.List;

/**
 * 拦截器配置
 *
 * @author jarome
 * @date 2017/12/29
 **/
@Component
public class WebMvcConfigAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new AuthenticationInterceptor());
        ir.addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new EncryptArgumentResolver());
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new EncryptBodyRturnValueHandler());
    }
}
