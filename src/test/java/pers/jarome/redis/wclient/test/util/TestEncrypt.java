package pers.jarome.redis.wclient.test.util;

import org.junit.Test;
import pers.jarome.redis.wclient.common.util.AesUtils;
import pers.jarome.redis.wclient.test.BaseTest;

/**
 * @author jarome
 * @date 2017/12/28
 **/
public class TestEncrypt {

    private String key = "abcfdgutks123411";

    private String iv = "asdcvghbjn45681d";
    private String id = "5wpvsj5ngzju8lo1";

    @Test
    public void t1(){
        String source = AesUtils.decode("33303dce1f9d0a4bba969436c11e3aacc85b900e8028abc236adad48dadead95f05aa7eaa51a2dc9cb70135a8959f3b6b4ec04313c404e64d9bcfc2cb75ac334","ab1cf4vdg4utk8z0","5wpvsj5ngzju8lo1",AesUtils.PADDING);
        System.out.println("解密后："+source);
    }

    @Test
    public void t2(){
        String source = AesUtils.decode("EC5C71FA7B3319F4B8D56CCE027205C989DD5A48AF53308EDB52C1611DB0A7BD3E2DCD4A9106BE167D25FBF63B983DDF","ab1cf4vdg4utk8z0","tg))nn4wka*e9y#b",AesUtils.PADDING);
        System.out.println("解密后："+source);
    }

}
