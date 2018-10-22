package message.processor.reverse.processor;

import message.core.log.Log;
import message.core.redis.RedisQueue;
import message.core.redis.RedisQueueConsumer;
import message.core.wrapper.MessageWrapper;

public class MessageProcessorQueueConsumer extends RedisQueueConsumer<MessageWrapper> {

    private final RedisQueue<MessageWrapper> messageDispatcherQueue;

    public MessageProcessorQueueConsumer(RedisQueue<MessageWrapper> messageProcessorQueue, RedisQueue<MessageWrapper> messageDispatcherQueue) {
        super(messageProcessorQueue);
        this.messageDispatcherQueue = messageDispatcherQueue;
    }

    @Override
    public void process(MessageWrapper messageWrapper) {
        Log.application.info("Process joke message {}", messageWrapper.getMessage().getText());
        messageWrapper.addResponse(new StringBuilder(messageWrapper.getMessage().getText()).reverse().toString());
        messageDispatcherQueue.push(messageWrapper);
    }

}