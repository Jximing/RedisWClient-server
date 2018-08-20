package pers.jarome.redis.wclient.rs.core.sys.api;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jarome.redis.wclient.common.util.AesUtils;
import pers.jarome.redis.wclient.common.util.RandomUtils;
import pers.jarome.redis.wclient.common.web.constant.HttpConstant;
import pers.jarome.redis.wclient.common.web.encrypt.anno.EncryptBody;
import pers.jarome.redis.wclient.common.web.encrypt.pojo.AesData;
import pers.jarome.redis.wclient.common.web.vo.EncryptEntity;
import pers.jarome.redis.wclient.common.web.vo.ResultEntity;
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
@RequestMapping(value = "/test")
public class TestApi extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TestApi.class);

    @EncryptBody
    @PostMapping(value = "/encryptTest")
    public UserDO encryptTest(@EncryptBody UserDO user) {
        return user;
    }


    @PostMapping(value = "/encode")
    @ResponseBody
    public ResultEntity decode(@RequestBody Object obj) {
        String resultJson;
        if (obj instanceof String || obj instanceof Number || obj instanceof Boolean) {
            resultJson = String.valueOf(obj);
        } else {
            resultJson = JSON.toJSONString(obj);
        }
        String iv = RandomUtils.randomString(AesUtils.APP_IV_LEN);
        String decodeData = AesUtils.encode(resultJson, HttpConstant.APP_KEY, iv, AesUtils.PADDING);
        AesData aesData = new AesData();
        aesData.setData(decodeData);
        aesData.setIv(iv);
        // decode = AesUtils.decode(aesData.getData(), HttpConstant.APP_KEY, aesData.getIv(), AesUtils.PADDING);
        return ResultEntity.successNoMessage(aesData);
    }

    @PostMapping(value = "/decode",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultEntity encode(@RequestBody AesData aesData) {
        String decode = AesUtils.decode(aesData.getData(), HttpConstant.APP_KEY, aesData.getIv(), AesUtils.PADDING);
        return ResultEntity.successNoMessage(decode);
    }

}
