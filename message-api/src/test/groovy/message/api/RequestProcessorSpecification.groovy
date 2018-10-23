package message.api

import message.api.controller.MessageController
import message.api.main.MessageApiConfiguration
import message.api.processor.RequestProcessor
import message.core.bot.BotService
import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
@EnableConfigurationProperties
@SpringBootTest(classes = [MessageController, RequestProcessor, MessageApiConfiguration])
class RequestProcessorSpecification extends Specification {

    @Autowired
    private BotService botService

    @Autowired
    private MessageController messageController

    @Shared
    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("../docker-compose-test.yml")).withLocalCompose(true).start()

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
