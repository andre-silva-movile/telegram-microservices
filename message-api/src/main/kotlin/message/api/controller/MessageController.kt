package message.api.controller

import message.api.processor.RequestProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController {

    @Autowired
    private val requestProcessor: RequestProcessor? = null

    @PostMapping(value = "/telegram/{bot-id}")
    fun process(@PathVariable("bot-id") bot: Long?, @RequestBody body: String): ResponseEntity<String> {

        requestProcessor?.process(bot, body)
        return ResponseEntity.ok().build()
    }

}
