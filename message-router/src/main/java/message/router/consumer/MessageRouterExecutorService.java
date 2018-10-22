package message.router.consumer;

import message.core.bot.BotService;
import message.core.log.Log;
import message.core.wrapper.MessageWrapperQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class MessageRouterExecutorService {


    private ExecutorService executorService;

    @Autowired
    public MessageRouterExecutorService(BotService botService,
                                        ApplicationContext applicationContext,
                                        MessageWrapperQueue messageRouterQueue,
                                        @Value("#{processors.queue}") List<String> processors,
                                        @Value("${consumers.size:1}") Integer consumerSize) {
        Map<String, MessageWrapperQueue> messageWrapperQueueMap = processors.stream().collect(Collectors.toMap(s -> s, s -> applicationContext.getBean(s, MessageWrapperQueue.class)));

        ExecutorService executorService = Executors.newFixedThreadPool(consumerSize);
        for (int i = 0; i < consumerSize; i++) {
            MessageRouterQueueConsumer processor = new MessageRouterQueueConsumer(messageWrapperQueueMap, messageRouterQueue, botService);
            executorService.submit(processor);
        }
        Log.application.info("created {} consumers to route messages", consumerSize);
    }


    @PreDestroy
    public void deinit() {
        executorService.shutdown();
    }

}


