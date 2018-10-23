package message.api

import message.core.bot.Bot
import org.apache.commons.lang3.StringUtils


class RequestApiCreator {

    static Bot createBlankBot() {
        return Bot.builder().setName(StringUtils.EMPTY).setProcessor(StringUtils.EMPTY).setToken(StringUtils.EMPTY).build()
    }

    static String bodyTextValid() {
        return "{\"update_id\":53206021,\n" +
                "\"message\":{\"message_id\":683,\"from\":{\"id\":150370867,\"is_bot\":false,\"first_name\":\"Andr\\u00e9\",\"last_name\":\"Henrique\",\"username\":\"andrehsilva\",\"language_code\":\"en-BR\"},\"chat\":{\"id\":150370867,\"first_name\":\"Andr\\u00e9\",\"last_name\":\"Henrique\",\"username\":\"andrehsilva\",\"type\":\"private\"},\"date\":1540259186,\"text\":\"Oi\"}}"
    }

    static String bodyTextInvalid() {
        return "{\"update_id\":53206021,\n" +
                "\"message\":{\"message_id\":683,\"from\":{\"id\":150370867,\"is_bot\":false,\"first_name\":\"Andr\\u00e9\",\"last_name\":\"Henrique\",\"username\":\"andrehsilva\",\"language_code\":\"en-BR\"},\"chat\":{\"id\":150370867,\"first_name\":\"Andr\\u00e9\",\"last_name\":\"Henrique\",\"username\":\"andrehsilva\",\"type\":\"private\"},\"date\":1540259186}}"
    }

    static String bodyJsonInvalid() {
        return "{\"update_id\":53206021,\n" +
                "\"message\":\"message_id\":683,\"from\":\"id\":150370867,\"is_bot\":false,\"first_name\":\"Andr\\u00e9\",\"last_name\":\"Henrique\",\"username\":\"andrehsilva\",\"language_code\":\"en-BR\"},\"chat\":{\"id\":150370867,\"first_name\":\"Andr\\u00e9\",\"last_name\":\"Henrique\",\"username\":\"andrehsilva\",\"type\":\"private\"},\"date\":1540259186,\"text\":\"fsf\"}}"
    }

}