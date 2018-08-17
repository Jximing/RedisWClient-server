package pers.jarome.redis.wclient.common.web.vo;

import pers.jarome.redis.wclient.common.web.constant.ResultCode;

/**
 * 返回值格式
 *
 * @author jarome
 * @date 2017/12/23
 */
public class ResultEntity<T> {

    private ResultCode code;

    private T data;

    private String message;

    private String error;

    public ResultEntity() {
    }

    private ResultEntity(ResultCode code, T data, String message, String error) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.error = error;
    }


    /**
     * 请求成功
     * 不返回data
     *
     * @param message
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successNoData(String message) {
        return success(null, message);
    }


    /**
     * 成功返回
     * 不返回message
     *
     * @param data
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successNoMessage(E data) {
        return success(data, null);
    }


    /**
     * 成功返回
     *
     * @param data
     * @param message
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> success(E data, String message) {
        return new ResultEntity<E>(ResultCode.SUCCESS, data, message, null);
    }


    /**
     * 失败返回
     *
     * @param message
     * @return
     */
    public static ResultEntity failed(String message) {
        return new ResultEntity(ResultCode.FAILED, null, message, null);
    }

    /**
     * 错误返回
     *
     * @param errorMsg
     * @return
     */
    public static ResultEntity error(String errorMsg) {
        return new ResultEntity(ResultCode.ERROR, null, null, errorMsg);
    }

    public int getCode() {
        return code.value();
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
