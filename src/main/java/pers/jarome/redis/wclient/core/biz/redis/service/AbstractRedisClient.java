package pers.jarome.redis.wclient.core.biz.redis.service;

import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author jarome
 * @date 2017/10/11
 */
public abstract class AbstractRedisClient {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    public StringRedisTemplate getStringRedisTemplate() {
        return this.stringRedisTemplate;
    }

    /**
     * 切换database
     *
     * @param database
     */
    public void changeDatabase(Integer database) {
        Assert.notNull(database);
        if (database < 0 && database > 15) {
            throw new RuntimeException("redis database are not correct -" + database);
        }
        Assert.notNull(this.stringRedisTemplate);
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) this.stringRedisTemplate.getConnectionFactory();
        if (jedisConnectionFactory.getDatabase() != database) {
            jedisConnectionFactory.setDatabase(database);
            this.stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        }
    }
}
