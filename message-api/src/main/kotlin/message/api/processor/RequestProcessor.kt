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

    fun process(id: String?, body: String?): Boolean {
        try {
            body?.let {
                val update = GsonMapper.SNACK_CASE.deserialize(body, Update::class.java)
                return handleUpdate(id, update)
            } ?: run {
                Log.application.error("Unable to process request without valid body")
                return false
            }
        } catch (e: RuntimeException) {
            Log.application.error("Unable to process request without valid body: {}", e)
            return false
        }
    }

    private fun handleUpdate(id: String?, update: Update): Boolean {
        if (botService.get(id) == null) {
            Log.application.error("Unable to process message without bot entity")
            return false
        }
        val messageWrapper = MessageWrapper.builder().setMessage(update.message).setBot(id).setUpdateId(update.updateId).build()
        Log.application.info("Message sent to router queue")
        return messageRouterQueue.push(messageWrapper) > 0
    }

}