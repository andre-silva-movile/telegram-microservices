package message.processor.echo.processor

import message.core.log.Log
import message.core.redis.RedisQueue
import message.core.redis.RedisQueueConsumer
import message.core.wrapper.MessageWrapper

class MessageProcessorQueueConsumer(messageEchoProcessorQueue: RedisQueue<MessageWrapper>,
                                    private val messageDispatcherQueue: RedisQueue<MessageWrapper>) :
        RedisQueueConsumer<MessageWrapper>(messageEchoProcessorQueue) {

    override fun process(messageWrapper: MessageWrapper) {
        messageWrapper.message.text?.let {
            Log.application.info("Process echo message {}", messageWrapper.message.text)
            messageWrapper.addResponse(messageWrapper.message.text)
            messageDispatcherQueue.push(messageWrapper)
        }
    }

}
