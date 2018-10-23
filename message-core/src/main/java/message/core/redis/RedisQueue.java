package message.core.redis;

import message.core.mapper.Mapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class RedisQueue<T extends Serializable> {

    protected final JedisPool jedis;
    protected final String queue;
    protected final Mapper mapper;

    public RedisQueue(JedisPool jedis, Mapper mapper, String queue) {
        this.jedis = jedis;
        this.queue = queue;
        this.mapper = mapper;
    }

    public long push(T object) {
        Jedis jedisResource = jedis.getResource();
        try {
            long result = jedisResource.lpush(queue, mapper.serialize(object));
            jedisResource.close();
            return result;
        } catch (RuntimeException e) {
            jedisResource.close();
            return 0;
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        Jedis jedisResource = jedis.getResource();
        try {
            String content = jedisResource.lpop(queue);
            jedisResource.close();
            return mapper.deserialize(content, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (RuntimeException e) {
            jedisResource.close();
            return null;
        }
    }

}
