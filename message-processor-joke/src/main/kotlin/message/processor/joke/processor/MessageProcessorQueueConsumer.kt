package message.processor.joke.processor

import message.core.log.Log
import message.core.redis.RedisQueue
import message.core.redis.RedisQueueConsumer
import message.core.wrapper.MessageWrapper
import message.processor.joke.components.JokeService

class MessageProcessorQueueConsumer(messageJokeProcessorQueue: RedisQueue<MessageWrapper>,
                                    private val jokeService: JokeService,
                                    private val messageDispatcherQueue: RedisQueue<MessageWrapper>) :
        RedisQueueConsumer<MessageWrapper>(messageJokeProcessorQueue) {

    override fun process(messageWrapper: MessageWrapper) {
        Log.application.info("Process joke message {}", messageWrapper.message.text)
        val joke = jokeService.get()
        messageWrapper.addResponse(joke?.question)
        messageWrapper.addResponse(joke?.answer)
        messageDispatcherQueue.push(messageWrapper)
    }

}