package message.api.processor

import message.core.mapper.GsonMapper
import message.core.telegram.Update
import message.core.wrapper.MessageWrapper
import message.core.wrapper.MessageWrapperQueue
import org.springframework.stereotype.Component

@Component
class RequestProcessor(
        private val messageRouterQueue: MessageWrapperQueue
) {

    fun process(id: Long?, body: String?) {
        body?.let {
            val update = GsonMapper.SNACK_CASE.deserialize(body, Update::class.java)
            val messageWrapper = MessageWrapper.builder().setMessage(update.message).setBot(id).setUpdateId(update.updateId).build()
            messageRouterQueue.push(messageWrapper)
        } ?: run {
            throw IllegalArgumentException("Unable to process request without valid body")
        }
    }

}