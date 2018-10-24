package message.api

import message.api.controller.MessageController
import message.api.main.MessageApiConfiguration
import message.api.processor.RequestProcessor
import message.core.bot.BotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [MessageController, RequestProcessor, MessageApiConfiguration])
class MessageControllerSpecification extends ContainersSpecification {

    @Autowired
    private BotService botService

    @Autowired
    private MessageController messageController

    def "check process request"() {
        setup:
        botService.set("1", RequestApiCreator.createBlankBot())

        expect:
        messageController.process(id, body).statusCodeValue == output

        where:
        id   | body                                | output
        "1"  | RequestApiCreator.bodyJsonInvalid() | 400
        "1"  | RequestApiCreator.bodyTextInvalid() | 200
        "2"  | RequestApiCreator.bodyTextValid()   | 400
        "1"  | RequestApiCreator.bodyTextValid()   | 200
        null | RequestApiCreator.bodyTextValid()   | 400
        null | null                                | 400
        "1"  | null                                | 400

    }
}
