package message.core.queue;

import message.core.mapper.GsonMapper;
import message.core.redis.RedisConfiguration;
import message.core.wrapper.MessageWrapperQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import redis.clients.jedis.JedisPool;

@Import({RedisConfiguration.class})
@Configuration
public class MessageReverseProcessorQueueConfiguration {

    @Value("${redis.queue.processor-reverse}")
    private String name;

    @Bean("processor-reverse")
    @Autowired
    public MessageWrapperQueue messageReverseProcessorQueue(JedisPool redisPool) {
        return new MessageWrapperQueue(redisPool, GsonMapper.DEFAULT, name);
    }
}
