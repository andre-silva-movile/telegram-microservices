package message.api.controller

import message.api.processor.RequestProcessor
import message.core.log.Log
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
    fun process(@PathVariable("bot-id") bot: String?, @RequestBody body: String): ResponseEntity<String> {
        Log.application.info("New bot request from bot {} with body {}", bot, body)
        requestProcessor?.process(bot, body)
        return ResponseEntity.ok().build()
    }

}
