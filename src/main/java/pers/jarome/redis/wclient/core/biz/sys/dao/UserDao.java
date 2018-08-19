package pers.jarome.redis.wclient.core.biz.sys.dao;

import pers.jarome.redis.wclient.core.biz.sys.domain.UserDO;

/**
 * @author jarome
 * @date 2017/12/22
 **/
public interface UserDao {

    /**
     * 根据id查询User
     *
     * @param id
     * @return
     */
    UserDO getById(Integer id);

    /**
     * 根据username查询User
     *
     * @param username
     * @return
     */
    UserDO getByUsername(String username);

    /**
     * 新增user
     *
     * @param user
     * @return id
     */
    int addUser(UserDO user);

}
