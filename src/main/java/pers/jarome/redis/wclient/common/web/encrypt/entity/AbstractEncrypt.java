package pers.jarome.redis.wclient.common.web.encrypt.entity;

import com.alibaba.fastjson.JSON;
import pers.jarome.redis.wclient.common.web.encrypt.exception.EncryptException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
/**
 * 
 * AbstractEncrypt
 * @description AES加密操作抽象类
 * @author jiangliuhong
 */
public abstract class AbstractEncrypt {

    private boolean isBasicObject(Class clazz) {
        return (clazz == String.class || clazz == Number.class || clazz == Boolean.class);
    }

    public <T> T parseObject(String source, Class<T> type) {
        try {
            if (isBasicObject(type)) {
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

    public Object parseObject(String source, Type type) {
        try {
            Class clazz ;
            clazz = Class.forName(type.getTypeName());
            if (isBasicObject(clazz)) {
                Constructor<?> constructor = clazz.getConstructor(String.class);
                constructor.newInstance(source);
                return clazz;
            }
            return parseObject(source, clazz);
        } catch (Exception e) {
            throw new EncryptException("Object parsing error", e);
        }
    }
}
