package pers.jarome.redis.wclient.test.dao;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pers.jarome.redis.wclient.core.biz.sys.dao.UserDao;
import pers.jarome.redis.wclient.core.biz.sys.domain.UserDO;
import pers.jarome.redis.wclient.core.biz.sys.service.UserService;
import pers.jarome.redis.wclient.test.BaseTest;

/**
 * @author jarome
 * @date 2017/12/22
 **/
public class TestUserDao extends BaseTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Test
    public void t1() {
        UserDO user = userDao.getById(2);
        LOGGER.info(JSON.toJSONString(user));
    }

    @Test
    public void t2() {
        String id = userService.addUser("test", "test", "test");
        LOGGER.info("id : " + id);
    }

    @Test
    public void t3() {
        String id = userService.addUser("test12", "123456");
        LOGGER.info("id : " + id);
    }

}
