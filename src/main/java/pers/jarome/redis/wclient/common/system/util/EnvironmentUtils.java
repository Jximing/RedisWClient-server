package pers.jarome.redis.wclient.common.system.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;
import pers.jarome.redis.wclient.common.exception.NoEnvException;

/**
 * 
 * EnvironmentUtils
 * @description 环境变量相关工具类
 * @author jiangliuhong
 * @date 2018/8/12 0:31
 * @version 1.0
 */
public class EnvironmentUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentUtils.class);

    /**
     * 获取环境变量
     * @param envName
     * @execption pers.jarome.redis.wclient.common.exception.NoEnvException
     * @return
     */
    public static String getEnv(String envName){
        //获取环境变量
        String rcHomeEnv = System.getenv(envName);
        if (StringUtils.isBlank(rcHomeEnv)) {
            LOGGER.error("没有找到环境变量:" + envName);
            throw new NoEnvException("没有找到环境变量:" + envName);
        }
        return rcHomeEnv;
    }
}
