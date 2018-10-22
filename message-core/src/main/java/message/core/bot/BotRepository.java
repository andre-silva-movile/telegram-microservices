package message.core.bot;

import message.core.mapper.Mapper;
import message.core.redis.RedisCollection;
import redis.clients.jedis.Jedis;

public class BotRepository extends RedisCollection<Bot> {

    public BotRepository(Jedis jedis, Mapper mapper, String collection) {
        super(jedis, mapper, collection);
    }
}
