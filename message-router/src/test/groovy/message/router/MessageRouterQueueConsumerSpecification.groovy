package message.router

import message.core.bot.BotService
import message.core.wrapper.MessageWrapperQueue
import message.router.consumer.MessageRouterQueueConsumer
import message.router.main.MessageRouterConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [MessageRouterConfiguration])
class MessageRouterQueueConsumerSpecification extends ContainersSpecification {

    @Autowired
    private BotService botService

    @Autowired
    private MessageWrapperQueue messageRouterQueue;

    @Autowired
    @Qualifier("processor-echo")
    private MessageWrapperQueue messageEchoProcessorQueue;

    @Autowired
    @Qualifier("processor-joke")
    private MessageWrapperQueue messageJokeProcessorQueue;

    @Autowired
    @Qualifier("processor-reverse")
    private MessageWrapperQueue messageReverseProcessorQueue;

    def "send new message to processor"() {
        setup:
        botService.set("1", MessageRouterCreator.createBot("processor-echo"))
        botService.set("2", MessageRouterCreator.createBot("processor-joke"))
        botService.set("3", MessageRouterCreator.createBot("processor-reverse"))
        def messageWrapperQueueMap = ["processor-echo": messageEchoProcessorQueue, "processor-joke": messageJokeProcessorQueue, "processor-reverse": messageReverseProcessorQueue]
        def messageRouterQueueConsumer = new MessageRouterQueueConsumer(messageWrapperQueueMap, messageRouterQueue, botService)

        when:
        messageRouterQueueConsumer.process(MessageRouterCreator.createMessageWrapper("1", 1))
        messageRouterQueueConsumer.process(MessageRouterCreator.createMessageWrapper("2", 2))
        messageRouterQueueConsumer.process(MessageRouterCreator.createMessageWrapper("3", 3))

        then:
        messageEchoProcessorQueue.pop().updateId == 1
        messageJokeProcessorQueue.pop().updateId == 2
        messageReverseProcessorQueue.pop().updateId == 3
    }

}
