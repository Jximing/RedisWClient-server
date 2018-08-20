package pers.jarome.redis.wclient.common.web.encrypt.method.resolver;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pers.jarome.redis.wclient.common.web.encrypt.anno.EncryptBody;
import pers.jarome.redis.wclient.common.web.encrypt.entity.Encrypt;
import pers.jarome.redis.wclient.common.web.encrypt.exception.EncryptException;
import pers.jarome.redis.wclient.common.web.encrypt.method.AbstractEcryptMappingHadler;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * EncryptArgumentResolver
 *
 * @author jiangliuhong
 * @description 加密解析器
 * @date 2018/8/17 9:35
 */
public class EncryptArgumentResolver extends AbstractEcryptMappingHadler implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return hasEncryptAnnotaion(parameter);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        EncryptBody encryptBody = parameter.getAnnotatedElement().getAnnotation(EncryptBody.class);
        String body = getRequestBody(webRequest);
        Object resultData = null;
        Type type = parameter.getNestedGenericParameterType();
        if (StringUtils.isNotBlank(body)) {
            Encrypt encrypt = getEncrypt(encryptBody.method());
            if (encrypt == null) {
                throw new EncryptException("Not Found Encrypt.");
            }
            resultData = encrypt.decode(body, type);
        }
        if (resultData == null) {
            //body为空或解密后为空，为方便方法内调用，new一个空对象
            Class<?> clazz = Class.forName(type.getTypeName());
            resultData = clazz.newInstance();
        }
        return resultData;
    }

    private Boolean hasEncryptAnnotaion(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(EncryptBody.class);
    }

    private String getRequestBody(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        ServletInputStream inputStream = servletRequest.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder body = new StringBuilder();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            body.append(str);
        }
        return body.toString();
    }
}
