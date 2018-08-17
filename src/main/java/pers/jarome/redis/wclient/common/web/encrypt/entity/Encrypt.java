package pers.jarome.redis.wclient.common.web.encrypt.entity;

import java.lang.reflect.Type;

public interface Encrypt {

    public Object encode(Object data);

    public String decode(String source);

    public <T> T decode(String source,Class<T> type);

    public <T> T decode(String source, Type type);

}
