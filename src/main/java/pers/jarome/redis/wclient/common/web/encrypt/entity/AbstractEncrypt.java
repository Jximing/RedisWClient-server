package pers.jarome.redis.wclient.common.web.encrypt.entity;

import com.alibaba.fastjson.JSON;
import pers.jarome.redis.wclient.common.web.encrypt.exception.EncryptException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public abstract class AbstractEncrypt {

    public <T> T parseObject(String source, Class<T> type) {
        try {
            if (type == String.class || type == Number.class || type == Boolean.class) {
                Class<?> clazz = Class.forName(type.getName());
                Constructor<?> constructor = clazz.getConstructor(String.class);
                constructor.newInstance(source);
                return (T) clazz;
            }
            return JSON.parseObject(source, type);
        } catch (Exception e) {
            throw new EncryptException("Object parsing error", e);
        }
    }

    public <T> T parseObject(String source,Type type){
        return JSON.parseObject(source, type);
    }

}
