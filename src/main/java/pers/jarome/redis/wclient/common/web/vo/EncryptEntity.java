package pers.jarome.redis.wclient.common.web.vo;

import com.alibaba.fastjson.JSON;
import pers.jarome.redis.wclient.common.util.AesUtils;
import pers.jarome.redis.wclient.common.util.RandomUtils;
import pers.jarome.redis.wclient.common.web.constant.HttpConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 加密数据类型
 *
 * @author jarome
 * @date 2018/1/4
 **/
public class EncryptEntity {
    private String iv;
    private String data;

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * 构建加密返回值
     *
     * @param data
     * @return
     */
    public static EncryptEntity encodeEncrypt(Object data) {
        //对返回值进行加密
        if (data != null) {
            String resultJson = JSON.toJSONString(data);
            String iv = RandomUtils.randomString(AesUtils.APP_IV_LEN);
            String decodeData = AesUtils.encode(resultJson, HttpConstant.APP_KEY, iv, AesUtils.PADDING);
            EncryptEntity encryptEntity = new EncryptEntity();
            encryptEntity.setData(decodeData);
            encryptEntity.setIv(iv);
            return encryptEntity;
        }
        return null;
    }

    /**
     * 解析加密参数
     *
     * @return
     */
    public <T> T decodeEncrypt(Class<T> type) {
        return JSON.parseObject(decodeEncrypt(),type);
    }

    /**
     * 解析加密参数
     * @return
     */
    public String decodeEncrypt(){
        return AesUtils.decode(this.getData(), HttpConstant.APP_KEY, this.getIv(), AesUtils.PADDING);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Map toMap() {
        Map map = new HashMap(4);
        map.put("iv", this.getIv());
        map.put("data", this.getData());
        return map;
    }

}
