package message.core.queue;

import message.core.mapper.GsonMapper;
import message.core.redis.RedisConfiguration;
import message.core.wrapper.MessageWrapperQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import redis.clients.jedis.JedisPool;

@Import({RedisConfiguration.class})
public class MessageRouterQueueConfiguration {

    @Value("${redis.queue.router}")
    private String name;

    @Bean
    @Autowired
    public MessageWrapperQueue messageRouterQueue(JedisPool redisPool) {
        return new MessageWrapperQueue(redisPool.getResource(), GsonMapper.DEFAULT, name);
    }
}
