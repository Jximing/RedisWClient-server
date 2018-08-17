package pers.jarome.redis.wclient.core.biz.sys.service;

import java.io.IOException;

/**
 * @author jarome
 * @date 2017/12/23
 **/
public interface DbsysService {

    /**
     * 初始化数据库
     */
    void initDb() throws IOException;


}
