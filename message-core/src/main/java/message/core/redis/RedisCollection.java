package message.core.redis;

import message.core.mapper.Mapper;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class RedisCollection<T extends Serializable> {

    protected final JedisPool jedis;
    protected final String collection;
    protected final Mapper mapper;

    public RedisCollection(JedisPool jedis, Mapper mapper, String collection) {
        this.jedis = jedis;
        this.mapper = mapper;
        this.collection = StringUtils.join(collection, ":");
    }

    @SuppressWarnings("unchecked")
    public T get(String key) {
        try {
            Jedis resource = jedis.getResource();
            String content = resource.get(StringUtils.join(collection, key));
            resource.close();
            return mapper.deserialize(content, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (RuntimeException e) {
            return null;
        }
    }

    //TODO: create set with redis directives
    public String set(String key, T object) {
        try {
            Jedis resource = jedis.getResource();
            String result = resource.set(StringUtils.join(collection, key), mapper.serialize(object));
            resource.close();
            return result;
        } catch (RuntimeException e) {
            return null;
        }
    }


}

