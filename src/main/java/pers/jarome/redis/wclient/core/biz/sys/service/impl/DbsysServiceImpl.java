package pers.jarome.redis.wclient.core.biz.sys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;
import pers.jarome.redis.wclient.core.biz.sys.service.DbsysService;
import pers.jarome.redis.wclient.core.biz.sys.dao.DbsysDao;
import pers.jarome.redis.wclient.core.biz.sys.query.DbsysQuery;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jarome
 * @date 2017/12/23
 **/
@Service("dbsysService")
public class DbsysServiceImpl implements DbsysService {

    private static Logger LOGGER = LoggerFactory.getLogger(DbsysServiceImpl.class);

    @Autowired
    private DbsysDao dbsysDao;

    @Override
    public void initDb() throws IOException {
        DbsysQuery query = new DbsysQuery();
        query.setType("table");
        int count = dbsysDao.getCountByQuery(query);
        if (count <= 0) {
            LOGGER.info("读取 " + SystemConstants.CREATE_TABLE_NAME);
            //读取文件
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(SystemConstants.CREATE_TABLE_NAME);
            int tmp;
            List<String> sqlContents = new ArrayList<String>();
            String sqlContent = "";
            while ((tmp = is.read()) != -1) {
                if (((char) tmp) != '\r') {
                    sqlContent = sqlContent + (char) tmp;
                    if (((char) tmp) == ';') {
                        sqlContents.add(sqlContent);
                        sqlContent = "";
                    }
                }
            }
            //执行sql语句
            for (String sql : sqlContents) {
                LOGGER.info("执行语句 ：" + sql);
                dbsysDao.executeSql(sql);
            }
        }

    }

}
