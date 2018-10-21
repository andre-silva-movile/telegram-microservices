package message.processor.reverse.main;

import message.core.queue.MessageDispatcherQueueConfiguration;
import message.core.queue.MessageReverseProcessorQueueConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MessageDispatcherQueueConfiguration.class, MessageReverseProcessorQueueConfiguration.class})
public class MessageProcessorConfiguration {

}