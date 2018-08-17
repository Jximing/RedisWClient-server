package pers.jarome.redis.wclient.common.web.constant;

/**
 * 接口返回值状态
 *
 * @author jarome
 * @date 2017/12/23
 */
public enum ResultCode {

    /**
     * 请求成功
     */
    SUCCESS(0),
    /**
     * 请求错误
     */
    ERROR(-1),
    /**
     * 请求失败
     */
    FAILED(1);

    private Integer id;

    ResultCode(Integer id) {
        this.id = id;
    }

    public Integer value() {
        return id;
    }

}
