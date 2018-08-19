package pers.jarome.redis.wclient.rs.common.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.jarome.redis.wclient.common.web.vo.ResultEntity;

/**
 * @author jarome
 * @date 2017/12/23
 */
public abstract class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 失败返回
     *
     * @param message 消息
     * @return
     */
    protected ResultEntity failed(String message) {
        return ResultEntity.failed(message);
    }

    protected ResultEntity success(String message){
        return ResultEntity.successNoData(message);
    }

}
