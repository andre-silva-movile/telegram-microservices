package message.processor.echo.processor

import message.core.log.Log
import message.core.wrapper.MessageWrapperQueue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.annotation.PreDestroy

@Service
class MessageProcessorExecutorService @Autowired
constructor(@Qualifier("processor-echo") messageEchoProcessorQueue: MessageWrapperQueue,
            messageDispatcherQueue: MessageWrapperQueue,
            @Value("\${consumers.size:1}") consumerSize: Int?) {

    private val executorService: ExecutorService

    init {
        Log.application.info("Creating {} consumers to process echo", consumerSize)
        val executorService = Executors.newFixedThreadPool(consumerSize!!)
        for (i in 0 until consumerSize) {
            val processor = MessageProcessorQueueConsumer(messageEchoProcessorQueue, messageDispatcherQueue)
            executorService.submit(processor)
        }
        this.executorService = executorService
    }

    @PreDestroy
    fun deinit() {
        executorService.shutdown()
    }

}
