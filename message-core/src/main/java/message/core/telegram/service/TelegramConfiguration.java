package message.core.telegram.service;

import message.core.mapper.GsonMapper;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramConfiguration {

    @Bean
    @Autowired
    public TelegramService telegramService() {
        return new TelegramService(HttpClients.createDefault(), GsonMapper.DEFAULT);
    }
}
