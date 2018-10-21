package message.api.main

import message.core.queue.MessageRouterQueueConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(MessageRouterQueueConfiguration::class)
class MessageApiConfiguration