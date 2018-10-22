package message.core.redis;

import message.core.mapper.Mapper;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class RedisCollection<T extends Serializable> {

    protected final Jedis jedis;
    protected final String collection;
    protected final Mapper mapper;

    public RedisCollection(Jedis jedis, Mapper mapper, String collection) {
        this.jedis = jedis;
        this.mapper = mapper;
        this.collection = StringUtils.join(collection, ":");
    }

    @SuppressWarnings("unchecked")
    public T get(String key) {
        try {
            String content = jedis.get(StringUtils.join(collection, key));
            return mapper.deserialize(content, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (RuntimeException e) {
            return null;
        }
    }
    //TODO: create set with redis directives
    public String set(String key, T object) {
        try {
            return jedis.set(StringUtils.join(collection, key), mapper.serialize(object));
        } catch (RuntimeException e) {
            return null;
        }
    }


}

