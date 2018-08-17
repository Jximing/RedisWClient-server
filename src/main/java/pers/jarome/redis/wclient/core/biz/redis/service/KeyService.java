package pers.jarome.redis.wclient.core.biz.redis.service;

import java.util.Set;

/**
 * @author jarome
 * @date 2017/12/22
 **/
public interface KeyService extends BaseRedisServie {

    Set<String> getKeys(String pattern);

}
