package pers.jarome.redis.wclient.test.util;

import org.junit.Test;
import pers.jarome.redis.wclient.common.util.Md5Utils;

/**
 * @author jarome
 * @date 2017/12/23
 */
public class TestUtil {

    @Test
    public void tMd5(){
        String encode = Md5Utils.encode("123456",null,0);
        System.out.println(encode);
    }
}
