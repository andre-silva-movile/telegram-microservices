package message.dispatcher.consumer

import message.core.telegram.service.TelegramService
import message.core.wrapper.MessageWrapperQueue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.annotation.PreDestroy

@Service
class MessageDispatcherExecutorService @Autowired
constructor(messageDispatcherQueue: MessageWrapperQueue,
            telegramService: TelegramService,
            @Value("\${consumers.size:1}") consumerSize: Int?,
            @Value("#{bots.configuration}") configuration: Map<Long, String>) {

    private val executorService: ExecutorService

    init {
        val executorService = Executors.newFixedThreadPool(consumerSize!!)
        for (i in 0 until consumerSize) {
            val processor = MessageDispatcherQueueConsumer(messageDispatcherQueue, telegramService, configuration)
            executorService.submit(processor)
        }
        this.executorService = executorService
    }

    @PreDestroy
    fun deinit() {
        executorService.shutdown()
    }

}