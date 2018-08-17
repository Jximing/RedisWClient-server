package pers.jarome.redis.wclient.test.ot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import pers.jarome.redis.wclient.test.BaseTest;

import java.util.Set;

/**
 * @author jarome
 * @date 2017/12/21
 **/
public class TestCRedis extends BaseTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void t1() {
        Set<String> keys = stringRedisTemplate.keys("*");
        for (String key:keys){
            LOGGER.info(key);
        }
    }

    @Test
    public void t2() {
        LOGGER.info("keys foreach start");
        Set<String> keys = stringRedisTemplate.keys("*");
        LOGGER.info("key size : " + keys.size());
        for (String key : keys) {
            LOGGER.info(key);
        }
        LOGGER.info("keys foreach  end");
    }

    @Test
    public void t3(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("testkey","testvalue");
        String testkey = stringStringValueOperations.get("testkey");
        LOGGER.info(testkey);
    }

    @Test
    public void t4() {
        for (int i = 0; i <= 2; i++) {
            JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) stringRedisTemplate.getConnectionFactory();
            jedisConnectionFactory.setDatabase(i);
            stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
            ValueOperations valueOperations = stringRedisTemplate.opsForValue();
            String test = (String) valueOperations.get("test");
            LOGGER.info(test);
        }
    }

}
