package message.core.redis;

import message.core.mapper.Mapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class RedisQueue<T extends Serializable> {

    private final Jedis jedis;
    private final String queue;
    private final Mapper mapper;

    public RedisQueue(JedisPool redisPool, Mapper mapper, String queue) {
        this.jedis = redisPool.getResource();
        this.queue = queue;
        this.mapper = mapper;
    }

    public long push(T object) {
        try {
            return jedis.lpush(queue, mapper.serialize(object));
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        try {
            String content = jedis.lpop(queue);
            return mapper.deserialize(content, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (RuntimeException e) {
            return null;
        }
    }

}
