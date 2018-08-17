package pers.jarome.redis.wclient.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pers.jarome.redis.wclient.core.biz.redis.service.KeyService;
import pers.jarome.redis.wclient.test.BaseTest;

import java.util.Set;

/**
 * @author jarome
 * @date 2018/1/5
 **/
public class TestKeyService extends BaseTest {

    @Autowired
    @Qualifier("keyService")
    private KeyService keyService;

    @Test
    public void t1() {
        for (int i = 0; i < 2; i++) {
            keyService.changeDatabase(i);
            Set<String> keys = keyService.getKeys("*");
            for (String key : keys) {
                LOGGER.info(key);
            }
        }
    }
}
