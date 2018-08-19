package pers.jarome.redis.wclient.rs.core.sys.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import pers.jarome.redis.wclient.common.web.encrypt.anno.EncryptBody;
import pers.jarome.redis.wclient.core.biz.sys.domain.UserDO;
import pers.jarome.redis.wclient.rs.common.api.BaseController;

/**
 * TestController
 *
 * @author jiangliuhong
 * @version 1.0.0
 * @description 测试接口
 * @date 2018/8/17 11:22
 */
@Controller
public class TestApi extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TestApi.class);

    @EncryptBody
    @PostMapping(value = "/encryptTest")
    public UserDO encryptTest(@EncryptBody UserDO user) {
        return user;
    }
}
