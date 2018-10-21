package message.core.wrapper;

import message.core.mapper.Mapper;
import message.core.redis.RedisQueue;
import redis.clients.jedis.JedisPool;

public class MessageWrapperQueue extends RedisQueue<MessageWrapper> {

    public MessageWrapperQueue(JedisPool redisPool, Mapper mapper, String queue) {
        super(redisPool, mapper, queue);
    }
}
