package pers.jarome.redis.wclient.common.web.annotaion.method;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import pers.jarome.redis.wclient.common.web.encrypt.anno.EncryptBody;
import pers.jarome.redis.wclient.common.web.encrypt.method.handler.EncryptBodyRturnValueHandler;
import pers.jarome.redis.wclient.common.web.encrypt.method.resolver.EncryptArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * EncryptRequestResponseBodyMethodProcessor
 * <p>
 * 使用了新的实现方式
 * </p>
 * <p>
 * 不再通过重写RequestResponseBodyMethodProcessor来实现
 * </p>
 *<p>
 *    新的实现逻辑请查看
 *</p>
 * @see EncryptBodyRturnValueHandler
 * @see EncryptArgumentResolver
 * @author jiangliuhong
 * @description 加密实体读取
 * @date 2018/8/15 18:46
 */
@Deprecated
public class EncryptRequestResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {

    public EncryptRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    protected Object readWithMessageConverters(NativeWebRequest webRequest, MethodParameter parameter, Type paramType)
            throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
        //该方法与父类中一致，不过后面可能会进行重构
        Object arg = readWithMessageConverters(inputMessage, parameter, paramType);
        if (arg == null) {
            if (checkRequired(parameter)) {
                throw new HttpMessageNotReadableException("Required request body is missing: " +
                        parameter.getMethod().toGenericString());
            }
        }
        return arg;
    }

//    handleReturnValue

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

    @Override
    protected <T> void writeWithMessageConverters(T value, MethodParameter returnType, ServletServerHttpRequest inputMessage, ServletServerHttpResponse outputMessage)
            throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        super.writeWithMessageConverters(value, returnType, inputMessage, outputMessage);
    }

}
