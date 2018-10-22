package message.dispatcher.consumer

import message.core.bot.BotService
import message.core.log.Log
import message.core.redis.RedisQueue
import message.core.redis.RedisQueueConsumer
import message.core.telegram.service.TelegramService
import message.core.wrapper.MessageWrapper

class MessageDispatcherQueueConsumer(messageDispatcherQueue: RedisQueue<MessageWrapper>,
                                     private val botService: BotService,
                                     private val telegramService: TelegramService) :
                                     RedisQueueConsumer<MessageWrapper>(messageDispatcherQueue) {

    override fun process(messageWrapper: MessageWrapper) {
        val bot = botService.get(messageWrapper.bot)
        bot?.let {
            Log.application.info("Message wrapper received to dispatch to bot")
            messageWrapper.response?.forEach({ telegramService.sendMessage(messageWrapper.message.from.id.toString(), it, bot.token) })
        } ?: run {
            throw IllegalStateException("Unable to dispatch message without bot configuration")
        }
    }

}

