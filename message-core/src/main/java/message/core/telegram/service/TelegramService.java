package message.core.telegram.service;

import com.google.common.collect.ImmutableMap;
import message.core.log.Log;
import message.core.mapper.Mapper;
import message.core.telegram.Message;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class TelegramService {

    private final Mapper mapper;
    private final HttpClient httpClient;
    private final TelegramRequestCreator requestCreator;

    public TelegramService(HttpClient httpClient, Mapper mapper) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.requestCreator = new TelegramRequestCreator();
    }

    public Message sendMessage(String chatId, String message, String token) {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(message)) {
            Log.core.error("Unable to send message without token or message");
            return null;
        }
        try {
            Log.core.info("Request send message to chat {}", chatId);
            Map<String, String> bodyObject = ImmutableMap.of("chat_id", chatId, "text", message);
            HttpRequestBase request = requestCreator.sendMessage(mapper.serialize(bodyObject), token);
            HttpResponse response = httpClient.execute(request);
            if (Integer.valueOf(200).equals(response.getStatusLine().getStatusCode())) {
                Log.core.info("Message sent to chat {}", chatId);
                return mapper.deserialize(EntityUtils.toString(response.getEntity(), "UTF-8"), Message.class);
            } else {
                Log.core.error("Unable to send message without token or message");
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

}
