package pers.jarome.redis.wclient.core.biz.sys.dao;

import org.apache.ibatis.annotations.Param;
import pers.jarome.redis.wclient.core.biz.sys.query.DbsysQuery;

/**
 * 数据库系统相关方法
 *
 * @author jarome
 * @date 2017/12/23
 **/
public interface DbsysDao {

    /**
     * 计算数量
     *
     * @param query
     * @return
     */
    int getCountByQuery(DbsysQuery query);

    /**
     * 执行sql语句
     *
     * @param sql
     * @return
     */
    int executeSql(@Param("sql") String sql);

}
