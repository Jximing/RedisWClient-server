package pers.jarome.redis.wclient.app.properties;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SysProperties
 *
 * @author jiangliuhong
 * @description 系统相关配置
 * @date 2018/8/18 10:54
 */
@Configuration
@ConfigurationProperties(prefix = SysProperties.PREFIX_NAME)
public class SysProperties {

    public static final String PREFIX_NAME = "rwc.sys";

    private static final Log LOG = LogFactory.getLog(SysProperties.class);

    /**
     * 超级管理员用户名
     */
    private String admin;
    /**
     * 系统环境根目录
     */
    private String environmentHome;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getEnvironmentHome() {
        return environmentHome;
    }

    public void setEnvironmentHome(String environmentHome) {
        this.environmentHome = environmentHome;
    }
}
