package pers.jarome.redis.wclient.app.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pers.jarome.redis.wclient.app.properties.SysProperties;
import pers.jarome.redis.wclient.common.constant.CacheConstants;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;
import pers.jarome.redis.wclient.core.biz.sys.domain.UserDo;
import pers.jarome.redis.wclient.core.biz.sys.service.CacheService;
import pers.jarome.redis.wclient.core.biz.sys.service.UserService;

/**
 * 检测是否需要安装
 * （安装：展示install页面，引导用户设置管理员密码）
 *
 * @author jarome
 * @date 2017/12/23
 **/
@Component
@Order(2)
public class CheckInstallRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckInstallRunner.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("mapCacheServie")
    private CacheService cacheServie;
    @Autowired
    private SysProperties sysProperties;

    @Override
    public void run(String... strings){
        LOGGER.info("CheckInstallRunner running");
        UserDo user = userService.getByUsername(sysProperties.getAdmin());
        if (user == null) {
            //user 为空，则需显示install页面
            cacheServie.put(CacheConstants.IS_INSTALL, true);
            LOGGER.info("show install page");
        } else {
            LOGGER.info("no install page");
        }
    }
}
