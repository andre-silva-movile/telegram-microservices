package message.dispatcher.main

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration(value = "bots")
@ConfigurationProperties(prefix = "bots")
class MessageRouterPropertiesConfiguration {

    var configuration: Map<Long, String>? = null
}