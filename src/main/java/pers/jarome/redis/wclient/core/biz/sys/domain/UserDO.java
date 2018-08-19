package pers.jarome.redis.wclient.core.biz.sys.domain;

import pers.jarome.redis.wclient.common.system.constant.SystemConstants;
import pers.jarome.redis.wclient.common.util.DateUtils;

import java.util.Date;

/**
 * @author jarome
 * @date 2017/12/22
 **/
public class UserDO {

    private String id;
    private String username;
    private String password;
    private String salt;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreateTimeStr() {
        if(this.createTime != null) {
            return DateUtils.formatByDate(this.createTime, SystemConstants.TIMESTAMP_PATTERN);
        }else{
            return null;
        }
    }

    public void setCreateTime(String createTime) {
        this.createTime = DateUtils.formatByString(createTime, SystemConstants.TIMESTAMP_PATTERN);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
