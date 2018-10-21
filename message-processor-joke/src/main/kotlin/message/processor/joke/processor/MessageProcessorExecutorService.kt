package message.processor.joke.processor

import message.core.mapper.GsonMapper
import message.core.wrapper.MessageWrapperQueue
import message.processor.joke.components.JokeService
import org.apache.http.impl.client.HttpClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.annotation.PreDestroy

@Service
class MessageProcessorExecutorService @Autowired
constructor(@Qualifier("processor-joke") messageJokeProcessorQueue: MessageWrapperQueue,
            messageDispatcherQueue: MessageWrapperQueue,
            @Value("\${consumers.size:1}") consumerSize: Int?) {

    private val executorService: ExecutorService

    init {
        val executorService = Executors.newFixedThreadPool(consumerSize!!)
        val jokeService = JokeService(HttpClients.createDefault(), GsonMapper.DEFAULT)
        for (i in 0 until consumerSize) {
            val processor = MessageProcessorQueueConsumer(messageJokeProcessorQueue, jokeService, messageDispatcherQueue)
            executorService.submit(processor)
        }
        this.executorService = executorService
    }

    @PreDestroy
    fun deinit() {
        executorService.shutdown()
    }

}
