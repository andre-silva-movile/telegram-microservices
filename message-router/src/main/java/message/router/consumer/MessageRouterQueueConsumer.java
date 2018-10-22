package message.router.consumer;

import message.core.bot.Bot;
import message.core.bot.BotService;
import message.core.log.Log;
import message.core.redis.RedisQueue;
import message.core.redis.RedisQueueConsumer;
import message.core.wrapper.MessageWrapper;
import message.core.wrapper.MessageWrapperQueue;

import java.util.Map;

public class MessageRouterQueueConsumer extends RedisQueueConsumer<MessageWrapper> {

    private final BotService botService;
    private Map<String, MessageWrapperQueue> messageWrapperQueueMap;

    public MessageRouterQueueConsumer(Map<String, MessageWrapperQueue> messageWrapperQueueMap, RedisQueue<MessageWrapper> messageRouterQueue, BotService botService) {
        super(messageRouterQueue);
        this.messageWrapperQueueMap = messageWrapperQueueMap;
        this.botService = botService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(MessageWrapper messageWrapper) {
        Bot bot = botService.get(messageWrapper.getBot());
        if (bot == null) {
            throw new IllegalStateException("Unable to route message without bot configuration");
        }
        Log.application.info("Routing message to {}", bot.getProcessor());
        messageWrapperQueueMap.get("processor-echo").push(messageWrapper);
    }

}
