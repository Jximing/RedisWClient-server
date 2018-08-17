package pers.jarome.redis.wclient.core.biz.sys.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jarome.redis.wclient.common.constant.AuthConstants;
import pers.jarome.redis.wclient.common.exception.NoUserException;
import pers.jarome.redis.wclient.common.exception.PasswordMissException;
import pers.jarome.redis.wclient.common.util.AuthUtils;
import pers.jarome.redis.wclient.common.util.Md5Utils;
import pers.jarome.redis.wclient.common.util.RandomUtils;
import pers.jarome.redis.wclient.core.biz.sys.service.UserService;
import pers.jarome.redis.wclient.core.biz.sys.dao.UserDao;
import pers.jarome.redis.wclient.core.biz.sys.domain.UserDo;

import java.util.Date;

/**
 * @author jarome
 * @date 2017/12/22
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public String login(String username, String password) {
        UserDo user = getByUsername(username);
        if (user == null) {
            throw new NoUserException();
        }
        password = Md5Utils.encode(password, user.getSalt(), AuthConstants.HASH_NUM);
        if (!StringUtils.equals(user.getPassword(), password)) {
            throw new PasswordMissException();
        }
        return AuthUtils.createToken(user.getUsername(), AuthConstants.TOKEN_PERIOD_TIME);
    }

    @Override
    public UserDo getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public String addUser(String username, String password, String salt) {
        UserDo user = new UserDo();
        user.setId(RandomUtils.uuid());
        user.setUsername(username);
        user.setPassword(Md5Utils.encode(password, salt, AuthConstants.HASH_NUM));
        user.setSalt(salt);
        user.setCreateTime(new Date());
        userDao.addUser(user);
        return user.getId();
    }

    @Override
    public String addUser(String username, String password) {
        return addUser(username, password, RandomUtils.randomString(AuthConstants.SALT_LENGTH));
    }
}
