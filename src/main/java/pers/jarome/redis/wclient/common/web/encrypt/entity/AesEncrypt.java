package pers.jarome.redis.wclient.common.web.encrypt.entity;

import com.alibaba.fastjson.JSON;
import pers.jarome.redis.wclient.common.util.AesUtils;
import pers.jarome.redis.wclient.common.util.RandomUtils;
import pers.jarome.redis.wclient.common.web.constant.HttpConstant;
import pers.jarome.redis.wclient.common.web.encrypt.pojo.AesData;

import java.lang.reflect.Type;

public class AesEncrypt extends AbstractEncrypt implements Encrypt {

    @Override
    public Object encode(Object data) {
        if (data != null) {
            String resultJson = JSON.toJSONString(data);
            String iv = RandomUtils.randomString(AesUtils.APP_IV_LEN);
            String decodeData = AesUtils.encode(resultJson, HttpConstant.APP_KEY, iv, AesUtils.PADDING);
            AesData aesData = new AesData();
            aesData.setData(decodeData);
            aesData.setIv(iv);
            return aesData;
        }
        return null;
    }

    @Override
    public String decode(String source) {
        AesData aesData = JSON.parseObject(source, AesData.class);
        return AesUtils.decode(aesData.getData(), HttpConstant.APP_KEY, aesData.getIv(), AesUtils.PADDING);
    }

    @Override
    public <T> T decode(String source, Class<T> type) {
        return parseObject(decode(source), type);
    }

    @Override
    public <T> T decode(String source, Type type) {
        return parseObject(decode(source),type);
    }
}
