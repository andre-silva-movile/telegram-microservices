package message.router.main;

import message.core.queue.MessageEchoProcessorQueueConfiguration;
import message.core.queue.MessageJokeProcessorQueueConfiguration;
import message.core.queue.MessageReverseProcessorQueueConfiguration;
import message.core.queue.MessageRouterQueueConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MessageRouterQueueConfiguration.class, MessageEchoProcessorQueueConfiguration.class,
        MessageJokeProcessorQueueConfiguration.class, MessageReverseProcessorQueueConfiguration.class})
public class MessageRouterConfiguration {

}