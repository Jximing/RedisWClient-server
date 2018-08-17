package pers.jarome.redis.wclient.core.biz.redis.service.impl;

import org.springframework.stereotype.Service;
import pers.jarome.redis.wclient.core.biz.redis.service.AbstractRedisClient;
import pers.jarome.redis.wclient.core.biz.redis.service.KeyService;

import java.util.Set;

/**
 * @author jarome
 * @date 2018/1/5
 **/
@Service("keyService")
public class KeyServiceImpl extends AbstractRedisClient implements KeyService {
    @Override
    public Set<String> getKeys(String pattern) {
        return getStringRedisTemplate().keys(pattern);
    }
}
