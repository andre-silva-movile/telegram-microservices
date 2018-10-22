package message.dispatcher.main

import message.core.bot.BotConfiguration
import message.core.queue.MessageDispatcherQueueConfiguration
import message.core.telegram.service.TelegramConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(MessageDispatcherQueueConfiguration::class, TelegramConfiguration::class, BotConfiguration::class)
class MessageDispatcherConfiguration