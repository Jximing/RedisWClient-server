package pers.jarome.redis.wclient.common.web.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pers.jarome.redis.wclient.common.util.AuthUtils;
import pers.jarome.redis.wclient.common.web.annotaion.anno.Authentication;
import pers.jarome.redis.wclient.common.web.constant.HeaderName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 *
 * @author jarome
 * @date 2017/12/29
 **/
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检测@Authentication
        HandlerMethod methodHandler = (HandlerMethod) handler;
        Authentication auth = methodHandler.getMethodAnnotation(Authentication.class);
        if (auth != null && auth.check()) {
            String token = request.getHeader(HeaderName.TOKEN_NAME);
            AuthUtils.resolveToken(token);
        }
        return true;
    }
}
