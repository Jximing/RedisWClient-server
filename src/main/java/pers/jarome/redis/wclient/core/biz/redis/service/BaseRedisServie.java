package pers.jarome.redis.wclient.core.biz.redis.service;

/**
 * @author jarome
 * @date 2018/1/5
 **/
public interface BaseRedisServie {

    /**
     * 切换database
     *
     * @param database
     */
    void changeDatabase(Integer database);
}
