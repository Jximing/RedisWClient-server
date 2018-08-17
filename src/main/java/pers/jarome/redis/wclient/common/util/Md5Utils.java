package pers.jarome.redis.wclient.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5加密工具类
 *
 * @author jarome
 * @date 2017/12/23
 */
public class Md5Utils {

    /**
     * @param source
     * @return
     */
    public static String encode(String source) {
        return DigestUtils.md5Hex(source);
    }

    /**
     * @param source
     * @param salt
     * @return
     */
    public static String encode(String source, String salt) {
        if (salt != null) {
            source = source + salt;
        }
        return encode(source);
    }

    /**
     * @param source
     * @param salt
     * @param hashNum 散列次数
     * @return
     */
    public static String encode(String source, String salt, int hashNum) {
        String res = encode(source, salt);
        if (hashNum >= 1) {
            for (int i = 1; i <= hashNum; i++) {
                res = encode(res);
            }
        }
        return res;

    }

}
