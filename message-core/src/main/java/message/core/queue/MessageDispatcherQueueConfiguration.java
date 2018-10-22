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
public class MessageDispatcherQueueConfiguration {

    @Value("${redis.queue.dispatcher}")
    private String name;

    @Bean
    @Autowired
    public MessageWrapperQueue messageDispatcherQueue(JedisPool redisPool) {
        return new MessageWrapperQueue(redisPool, GsonMapper.DEFAULT, name);
    }
}
