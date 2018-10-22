package message.router.consumer;

import message.core.bot.Bot;
import message.core.bot.BotService;
import message.core.log.Log;
import message.core.redis.RedisQueue;
import message.core.redis.RedisQueueConsumer;
import message.core.wrapper.MessageWrapper;
import org.springframework.context.ApplicationContext;

public class MessageRouterQueueConsumer extends RedisQueueConsumer<MessageWrapper> {

    private final ApplicationContext applicationContext;
    private final BotService botService;

    public MessageRouterQueueConsumer(ApplicationContext applicationContext, RedisQueue<MessageWrapper> messageRouterQueue, BotService botService) {
        super(messageRouterQueue);
        this.applicationContext = applicationContext;
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
        ((RedisQueue<MessageWrapper>) applicationContext.getBean(bot.getProcessor())).push(messageWrapper);
    }

}

