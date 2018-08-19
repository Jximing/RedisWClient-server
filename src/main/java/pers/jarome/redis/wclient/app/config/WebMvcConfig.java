package pers.jarome.redis.wclient.app.config;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import pers.jarome.redis.wclient.common.web.encrypt.method.handler.EncryptBodyRturnValueHandler;
import pers.jarome.redis.wclient.common.web.encrypt.method.resolver.EncryptArgumentResolver;
import pers.jarome.redis.wclient.common.web.interceptor.AuthenticationInterceptor;

import java.util.List;

/**
 * 
 * WebMvcConfig
 * @description Web环境配置
 * @author jiangliuhong
 * @date 2018/8/19 0:46
 */
@Component
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new AuthenticationInterceptor());
        ir.addPathPatterns("/api/*");
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
