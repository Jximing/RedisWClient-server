package pers.jarome.redis.wclient.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author jarome
 * @date 2017/12/23
 */
public class RandomUtils {

    /**
     * 生成指定长度的随机字符串
     *
     * @param length
     * @return
     */
    public static String randomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * uuid
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
