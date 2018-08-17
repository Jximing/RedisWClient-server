package pers.jarome.redis.wclient.core.biz.sys.dao;

import pers.jarome.redis.wclient.core.biz.sys.domain.UserDo;

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
    UserDo getById(Integer id);

    /**
     * 根据username查询User
     *
     * @param username
     * @return
     */
    UserDo getByUsername(String username);

    /**
     * 新增user
     *
     * @param user
     * @return id
     */
    int addUser(UserDo user);

}
