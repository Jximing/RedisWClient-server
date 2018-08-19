package pers.jarome.redis.wclient.rs.core.sys.param;
/**
 * 
 * LoginParam
 * @description 登录接口参数
 * @author jiangliuhong
 * @date 2018/8/19 15:40
 * @version 1.0.0
 */
public class LoginParam {

    private String username;
    private String password;
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
