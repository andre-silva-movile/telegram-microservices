package message.router

import message.core.bot.Bot
import message.core.mapper.GsonMapper
import message.core.telegram.Update
import message.core.wrapper.MessageWrapper
import org.apache.commons.lang3.StringUtils

class MessageRouterCreator {

    static Bot createBot(String processor) {
        return Bot.builder().setName(StringUtils.EMPTY).setProcessor(processor).setToken(StringUtils.EMPTY).build()
    }

    static MessageWrapper createMessageWrapper(String botId, Integer updateId) {
        def body = "{\"update_id\":53206021,\n" +
                "\"message\":{\"message_id\":683,\"from\":{\"id\":150370867,\"is_bot\":false,\"first_name\":\"Andr\\u00e9\",\"last_name\":\"Henrique\",\"username\":\"andrehsilva\",\"language_code\":\"en-BR\"},\"chat\":{\"id\":150370867,\"first_name\":\"Andr\\u00e9\",\"last_name\":\"Henrique\",\"username\":\"andrehsilva\",\"type\":\"private\"},\"date\":1540259186,\"text\":\"Oi\"}}"
        def update = GsonMapper.SNACK_CASE.deserialize(body, Update)
        return MessageWrapper.builder().setBot(botId).setMessage(update.getMessage()).setUpdateId(updateId).build()
    }

}
