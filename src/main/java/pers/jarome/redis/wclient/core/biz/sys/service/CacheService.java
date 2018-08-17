package pers.jarome.redis.wclient.core.biz.sys.service;

/**
 * 缓存服务接口
 *
 * @author jarome
 * @date 2017/12/23
 **/
public interface CacheService {

    /**
     * 获取value值
     *
     * @param key
     * @return
     */
    public Object get(String key);

    /**
     * 新增缓存
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value);

    /**
     * 得到字符串
     *
     * @param key
     * @return
     */
    public String getString(String key);

    /**
     * 得到布尔值
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key);

    /**
     * 删除缓存
     *
     * @param key
     */
    public void del(String key);

}
