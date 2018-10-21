package message.router.main;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration(value = "bots")
@ConfigurationProperties(prefix = "bots")
public class MessageRouterPropertiesConfiguration {

    private Map<Long, String> processors;

    public Map<Long, String> getProcessors() {
        return processors;
    }

    public void setProcessors(Map<Long, String> processors) {
        this.processors = processors;
    }

}