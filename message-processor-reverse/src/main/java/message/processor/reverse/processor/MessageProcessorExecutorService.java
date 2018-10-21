package message.processor.reverse.processor;

import message.core.wrapper.MessageWrapperQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MessageProcessorExecutorService {

    private final ExecutorService executorService;

    @Autowired
    public MessageProcessorExecutorService(@Qualifier("processor-reverse") MessageWrapperQueue messageProcessorQueue,
                                           MessageWrapperQueue messageDispatcherQueue,
                                           @Value("${consumers.size:1}") Integer consumerSize) {

        ExecutorService executorService = Executors.newFixedThreadPool(consumerSize);
        for (int i = 0; i < consumerSize; i++) {
            MessageProcessorQueueConsumer processor = new MessageProcessorQueueConsumer(messageProcessorQueue, messageDispatcherQueue);
            executorService.submit(processor);
        }
        this.executorService = executorService;
    }

    @PreDestroy
    public void deinit() {
        executorService.shutdown();
    }

}