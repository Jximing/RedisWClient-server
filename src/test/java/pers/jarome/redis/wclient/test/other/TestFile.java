package pers.jarome.redis.wclient.test.other;

import org.junit.Test;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jarome
 * @date 2017/12/22
 */
public class TestFile {

    @Test
    public void t1() {

        String redisWclientHome = System.getenv("REDIS_WCLIENT_HOME");
        System.out.println(redisWclientHome);
        String fileName = redisWclientHome + "/testDB.db";
        System.out.println(fileName);
        File file = new File(fileName);
        System.out.println("file exists : " + file.exists());
        System.out.println(file.getName());
    }


    @Test
    public void t2() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(SystemConstants.CREATE_TABLE_NAME);
        int tmp;
        String sqlContent = "";
        while ((tmp = is.read()) != -1) {
            if (((char) tmp) != '\r') {
                sqlContent = sqlContent + (char) tmp;
            }
        }
        System.out.println(sqlContent);
    }

    @Test
    public void t3() {
        String param = "test";
        Object obj = new Object();
        System.out.println(obj.toString());
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
