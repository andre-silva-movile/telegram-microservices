package message.core.bot;

import message.core.mapper.GsonMapper;
import message.core.redis.RedisConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import redis.clients.jedis.JedisPool;

@Import({RedisConfiguration.class})
public class BotConfiguration {

    @Value("${redis.collection.bot}")
    private String collection;

    @Bean
    @Autowired
    public BotService botService(BotRepository botRepository) {
        return new BotService(botRepository);
    }

    @Bean
    @Autowired
    public BotRepository botRepository(JedisPool redisPool){
        return new BotRepository(redisPool.getResource(), GsonMapper.DEFAULT, collection);
    }
}
