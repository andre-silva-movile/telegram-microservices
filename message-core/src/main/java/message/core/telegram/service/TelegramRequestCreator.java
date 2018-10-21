package message.core.telegram.service;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

public class TelegramRequestCreator {

    private final static String SEND_MESSAGE = "https://api.telegram.org/bot${bot-token}/sendMessage";

    public HttpRequestBase sendMessage(String message, String botToken) {
        HttpPost post = new HttpPost(StrSubstitutor.replace(SEND_MESSAGE, ImmutableMap.of("bot-token", botToken)));
        post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        post.setEntity(new StringEntity(message, "UTF-8"));
        return post;
    }
}
