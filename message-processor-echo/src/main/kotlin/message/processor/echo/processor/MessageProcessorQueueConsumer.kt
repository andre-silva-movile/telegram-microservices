package message.processor.echo.processor

import message.core.redis.RedisQueue
import message.core.redis.RedisQueueConsumer
import message.core.wrapper.MessageWrapper

class MessageProcessorQueueConsumer(messageEchoProcessorQueue: RedisQueue<MessageWrapper>,
                                    private val messageDispatcherQueue: RedisQueue<MessageWrapper>) :
        RedisQueueConsumer<MessageWrapper>(messageEchoProcessorQueue) {

    override fun process(messageWrapper: MessageWrapper) {
        messageWrapper.message.text?.let {
            messageWrapper.addResponse(messageWrapper.message.text)
            messageDispatcherQueue.push(messageWrapper)
        }
    }

}
