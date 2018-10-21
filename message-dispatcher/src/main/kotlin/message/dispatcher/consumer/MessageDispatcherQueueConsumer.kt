package message.dispatcher.consumer

import message.core.redis.RedisQueue
import message.core.redis.RedisQueueConsumer
import message.core.telegram.service.TelegramService
import message.core.wrapper.MessageWrapper

class MessageDispatcherQueueConsumer(messageDispatcherQueue: RedisQueue<MessageWrapper>,
                                     private val telegramService: TelegramService,
                                     private val configuration: Map<Long, String>) :
        RedisQueueConsumer<MessageWrapper>(messageDispatcherQueue) {

    override fun process(messageWrapper: MessageWrapper) {
        val token = configuration[messageWrapper.bot]
        messageWrapper.response?.forEach({ telegramService.sendMessage(messageWrapper.message.from.id.toString(), it, token) })
    }

}

