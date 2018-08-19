package pers.jarome.redis.wclient.core.biz.sys.service;

import pers.jarome.redis.wclient.core.biz.sys.domain.UserDO;

/**
 * @author jarome
 * @date 2017/12/22
 **/
public interface UserService {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return jwt生成的token
     * @throws pers.jarome.redis.wclient.common.exception.NoUserException
     * @throws pers.jarome.redis.wclient.common.exception.PasswordMissException
     */
    String login(String username, String password);

    /**
     * 根据用户名称查询
     *
     * @param username
     * @return
     */
    UserDO getByUsername(String username);


    /**
     * 新增用户
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param salt     用于加密的盐
     * @return id
     */
    String addUser(String username, String password, String salt);

    /**
     * 新增用户
     *
     * @param username 用户名称
     * @param password 用户密码
     * @return id
     */
    String addUser(String username, String password);
}
