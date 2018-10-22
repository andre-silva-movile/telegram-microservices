package message.core.wrapper;

import message.core.mapper.Mapper;
import message.core.redis.RedisQueue;
import redis.clients.jedis.Jedis;

public class MessageWrapperQueue extends RedisQueue<MessageWrapper> {

    public MessageWrapperQueue(Jedis jedis, Mapper mapper, String queue) {
        super(jedis, mapper, queue);
    }
}
