package message.router.main;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration(value = "processors")
@ConfigurationProperties(prefix = "processors")
public class MessageRouterPropertiesConfiguration {

    private List<String> queue;

    public List<String> getQueue() {
        return queue;
    }

    public void setQueue(List<String> queue) {
        this.queue = queue;
    }
}