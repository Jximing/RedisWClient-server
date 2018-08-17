package pers.jarome.redis.wclient.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.jarome.redis.wclient.RedisWclientApplication;

/**
 * @author jarome
 * @date 2017/12/21
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisWclientApplication.class)
public class BaseTest {

    protected static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @Test
    public void test() {
    }
}
