package message.dispatcher.main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("message.dispatcher")
@EnableAutoConfiguration
@EnableConfigurationProperties
class MessageDispatcherApplication

fun main(args: Array<String>) {
    SpringApplication.run(MessageDispatcherApplication::class.java, *args)
}