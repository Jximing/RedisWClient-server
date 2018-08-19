package pers.jarome.redis.wclient.rs.api.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import pers.jarome.redis.wclient.common.web.encrypt.anno.EncryptBody;
import pers.jarome.redis.wclient.core.biz.sys.domain.UserDo;
import pers.jarome.redis.wclient.rs.api.BaseController;

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
    public UserDo encryptTest(@EncryptBody UserDo user) {
        return user;
    }
}
