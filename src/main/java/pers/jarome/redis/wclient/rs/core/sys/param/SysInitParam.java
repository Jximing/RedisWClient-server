package pers.jarome.redis.wclient.rs.core.sys.param;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * SysInitParam
 * @description 初始化参数
 * @author jiangliuhong
 * @date 2018/8/19 15:29
 * @version 1.0.0
 */
public class SysInitParam {

    private String password;
    private String rePassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
