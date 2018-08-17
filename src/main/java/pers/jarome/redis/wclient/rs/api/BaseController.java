package pers.jarome.redis.wclient.rs.api;

import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterNameDiscoverer;
import pers.jarome.redis.wclient.common.web.vo.EncryptEntity;
import pers.jarome.redis.wclient.common.web.vo.ResultEntity;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author jarome
 * @date 2017/12/23
 */
public abstract class BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected ParameterNameDiscoverer parameterNameDiscoverer;

    /**
     * 失败返回
     *
     * @param message 消息
     * @return
     */
    protected ResultEntity failed(String message) {
        return ResultEntity.failed(message);
    }

    protected ResultEntity success(String message){
        return ResultEntity.successNoData(message);
    }

    /**
     * 泛解析参数
     *
     * @param encryptEntity      数据源
     * @param methodName         方法名称
     * @param methodParamTypeNum 除去附加参数的个数
     * @param attachParams       附加参数
     */
    protected EncryptEntity resolveEncrypt(EncryptEntity encryptEntity, String methodName, int methodParamTypeNum, Object... attachParams) {
        try {
            Assert.notNull(encryptEntity);
            Assert.notNull(methodName);
            Map map = encryptEntity.decodeEncrypt(Map.class);
            Method[] declaredMethods = this.getClass().getDeclaredMethods();
            int reallyMethodParamTypeNum = methodParamTypeNum + attachParams.length;
            for (Method method : declaredMethods) {
                if (StringUtils.equals(method.getName(), methodName) && method.getParameterCount() == reallyMethodParamTypeNum) {
                    Object[] paramArgs = new Object[reallyMethodParamTypeNum];
                    String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
                    for (int i = 0; i < reallyMethodParamTypeNum; i++) {
                        if (i < methodParamTypeNum) {
                            paramArgs[i] = map.get(parameterNames[i]);
                        } else if ((i - methodParamTypeNum) < attachParams.length) {
                            paramArgs[i] = attachParams[attachParams.length - i - 1 + methodParamTypeNum];
                        } else {
                            break;
                        }
                    }
                    Object resultData = method.invoke(this, paramArgs);
                    return EncryptEntity.encodeEncrypt(resultData);
                }
            }
            LOGGER.error("not found method :" + methodName);
            throw new RuntimeException("not found method :" + methodName);
        } catch (Exception e) {
            LOGGER.error("resolve error", e);
            throw new RuntimeException(e.getMessage());
        }
    }

}
