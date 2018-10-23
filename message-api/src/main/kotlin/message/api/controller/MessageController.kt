package message.api.controller

import message.api.processor.RequestProcessor
import message.core.log.Log
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
        private val requestProcessor: RequestProcessor
) {

    @PostMapping(value = "/telegram/{bot-id}")
    fun process(@PathVariable("bot-id") bot: String?, @RequestBody body: String?): ResponseEntity<String> {
        Log.application.info("New bot request from bot {} with body {}", bot, body)
        return if (requestProcessor.process(bot, body)) ResponseEntity.ok().build() else ResponseEntity.badRequest().build()
    }

}
