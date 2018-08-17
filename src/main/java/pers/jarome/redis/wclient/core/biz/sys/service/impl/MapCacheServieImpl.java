package pers.jarome.redis.wclient.core.biz.sys.service.impl;

import org.springframework.stereotype.Service;
import pers.jarome.redis.wclient.core.biz.sys.service.CacheService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存服务。该类使用内存作缓存
 *
 * @author jarome
 * @date 2017/12/23
 **/
@Service("mapCacheServie")
public class MapCacheServieImpl implements CacheService {

    private Map<String, Object> container = new ConcurrentHashMap<String, Object>();

    /**
     * 获取value值
     *
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        return container.get(key);
    }

    /**
     * 新增缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void put(String key, Object value) {
        this.container.put(key, value);
    }

    /**
     * 得到字符串
     *
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        Object val = get(key);
        return val != null ? val.toString() : null;
    }

    /**
     * 得到布尔值
     *
     * @param key
     * @return
     */
    @Override
    public boolean getBoolean(String key) {
        Object val = get(key);
        if (val == null) {
            return false;
        }
        return (Boolean) val;
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    @Override
    public void del(String key) {
        this.container.remove(key);
    }

}
