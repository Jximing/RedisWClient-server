package pers.jarome.redis.wclient.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pers.jarome.redis.wclient.common.constant.CacheConstants;
import pers.jarome.redis.wclient.core.biz.sys.dao.DbsysDao;
import pers.jarome.redis.wclient.core.biz.sys.query.DbsysQuery;
import pers.jarome.redis.wclient.core.biz.sys.service.CacheService;
import pers.jarome.redis.wclient.test.BaseTest;

/**
 * @author jarome
 * @date 2017/12/23
 **/
public class TestDbsysDao extends BaseTest {

    @Autowired
    private DbsysDao dbsysDao;
    @Autowired
    @Qualifier("mapCacheServie")
    private CacheService cacheServie;

    @Test
    public void t1() {
        DbsysQuery query = new DbsysQuery();
        query.setType("table");
        int count = dbsysDao.getCountByQuery(query);
        LOGGER.info("count : " + count);
    }

    @Test
    public void t2() {
        LOGGER.info("istall value : " + cacheServie.getBoolean(CacheConstants.IS_INSTALL));
    }

}
