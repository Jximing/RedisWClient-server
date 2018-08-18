package pers.jarome.redis.wclient.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SysDbProperties
 *
 * @author jiangliuhong
 * @description 系统数据库配置
 * @date 2018/8/18 11:07
 */
@Configuration
@ConfigurationProperties(prefix = SysDbProperties.PREFIX_NAME)
public class SysDbProperties {

    public static final String PREFIX_NAME = SysProperties.PREFIX_NAME + ".db";

    /**
     * 数据库文件地址
     */
    private String filePath;
    /**
     * druidMonitor 登录用户名
     */
    private String druidMonitorUsername = "admin";
    /**
     * druidMonitor 登录密码
     */
    private String druidMonitorPassword = "admin";

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDruidMonitorUsername() {
        return druidMonitorUsername;
    }

    public void setDruidMonitorUsername(String druidMonitorUsername) {
        this.druidMonitorUsername = druidMonitorUsername;
    }

    public String getDruidMonitorPassword() {
        return druidMonitorPassword;
    }

    public void setDruidMonitorPassword(String druidMonitorPassword) {
        this.druidMonitorPassword = druidMonitorPassword;
    }
}
