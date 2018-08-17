package pers.jarome.redis.wclient.common.web.encrypt.method.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import pers.jarome.redis.wclient.common.web.encrypt.anno.EncryptBody;
import pers.jarome.redis.wclient.common.web.encrypt.entity.Encrypt;
import pers.jarome.redis.wclient.common.web.encrypt.method.AbstractEcryptMappingHadler;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 
 * EncryptBodyRturnValueHandler
 * @description 加密实体返回值组装器
 * @author jiangliuhong
 * @date 2018/8/16 21:53
 */
public class EncryptBodyRturnValueHandler extends AbstractEcryptMappingHadler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.hasMethodAnnotation(EncryptBody.class);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        EncryptBody encryptBody = returnType.getMethodAnnotation(EncryptBody.class);
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter outWriter = response.getWriter();
        Encrypt encrypt = getEncrypt(encryptBody.method());
        Object encode = encrypt.encode(returnValue);
        String jsonString = "";
        if(encode!=null) {
            jsonString = JSON.toJSONString(encode);
        }
        outWriter.write(jsonString);
        outWriter.flush();
        outWriter.close();
    }
}
