package message.dispatcher.consumer

import message.core.bot.BotService
import message.core.log.Log
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
            botService: BotService,
            telegramService: TelegramService,
            @Value("\${consumers.size:1}") consumerSize: Int?) {

    private val executorService: ExecutorService

    init {
        Log.application.info("Creating {} consumers to dispatch", consumerSize)

        val executorService = Executors.newFixedThreadPool(consumerSize!!)
        for (i in 0 until consumerSize) {
            val processor = MessageDispatcherQueueConsumer(messageDispatcherQueue, botService, telegramService)
            executorService.submit(processor)
        }
        this.executorService = executorService
    }

    @PreDestroy
    fun deinit() {
        executorService.shutdown()
    }

}