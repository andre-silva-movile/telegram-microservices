package message.processor.echo.main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("message.processor.echo")
@EnableAutoConfiguration
@EnableConfigurationProperties
class MessageProcessorApplication

fun main(args: Array<String>) {
    SpringApplication.run(MessageProcessorApplication::class.java, *args)
}