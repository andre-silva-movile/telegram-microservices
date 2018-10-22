package message.api.processor

import message.core.bot.BotService
import message.core.log.Log
import message.core.mapper.GsonMapper
import message.core.telegram.Update
import message.core.wrapper.MessageWrapper
import message.core.wrapper.MessageWrapperQueue
import org.springframework.stereotype.Component

@Component
class RequestProcessor(
        private val messageRouterQueue: MessageWrapperQueue,
        private val botService: BotService
) {

    fun process(id: String?, body: String?) {
        body?.let {
            val update = GsonMapper.SNACK_CASE.deserialize(body, Update::class.java)
            if (botService.get(id) == null) {
                throw IllegalStateException("Unable to process message without bot entity")
            }
            val messageWrapper = MessageWrapper.builder().setMessage(update.message).setBot(id).setUpdateId(update.updateId).build()
            messageRouterQueue.push(messageWrapper)
            Log.application.info("Message sent to router queue")
        } ?: run {
            throw IllegalArgumentException("Unable to process request without valid body")
        }
    }

}