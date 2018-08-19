package pers.jarome.redis.wclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author jarome
 * @date 2017/10/10
 */
@SpringBootApplication(scanBasePackages = { "pers.jarome.redis.wclient" })
@MapperScan("pers.jarome.redis.wclient.core.biz.*.dao")
@ImportResource(locations = {"classpath:config/kaptcha.xml"})
public class RedisWclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisWclientApplication.class, args);
    }

}
