package message.router.consumer;

import message.core.wrapper.MessageWrapperQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class MessageRouterExecutorService {

    private final ExecutorService executorService;

    @Autowired
    public MessageRouterExecutorService(ApplicationContext applicationContext,
                                        MessageWrapperQueue messageRouterQueue,
                                        @Value("${consumers.size:1}") Integer consumerSize,
                                        @Value("#{bots.processors}") Map<Long, String> configuration) {

        Map<Long, MessageWrapperQueue> queues = configuration.entrySet().stream().collect(Collectors.toMap(
                e -> e.getKey(), e -> (MessageWrapperQueue) applicationContext.getBean(e.getValue())
        ));

        ExecutorService executorService = Executors.newFixedThreadPool(consumerSize);
        for (int i = 0; i < consumerSize; i++) {
            MessageRouterQueueConsumer processor = new MessageRouterQueueConsumer(messageRouterQueue, queues);
            executorService.submit(processor);
        }
        this.executorService = executorService;
    }

    @PreDestroy
    public void deinit() {
        executorService.shutdown();
    }

}