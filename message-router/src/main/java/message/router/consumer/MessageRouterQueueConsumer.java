package message.router.consumer;

import message.core.redis.RedisQueue;
import message.core.redis.RedisQueueConsumer;
import message.core.wrapper.MessageWrapper;
import message.core.wrapper.MessageWrapperQueue;

import java.util.Map;

public class MessageRouterQueueConsumer extends RedisQueueConsumer<MessageWrapper> {

    private Map<Long, MessageWrapperQueue> queues;

    public MessageRouterQueueConsumer(RedisQueue<MessageWrapper> messageRouterQueue, Map<Long, MessageWrapperQueue> queues) {
        super(messageRouterQueue);
        this.queues = queues;
    }

    @Override
    public void process(MessageWrapper messageWrapper) {
        queues.get(messageWrapper.getBot()).push(messageWrapper);
    }

}

