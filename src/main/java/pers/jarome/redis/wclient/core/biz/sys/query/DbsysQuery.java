package pers.jarome.redis.wclient.core.biz.sys.query;

import pers.jarome.redis.wclient.core.common.query.BaseQuery;

/**
 * @author jarome
 * @date 2017/12/23
 **/
public class DbsysQuery extends BaseQuery {

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
