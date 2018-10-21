package message.api.main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@ComponentScan("message.api")
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableWebMvc
class MessageApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(MessageApiApplication::class.java, *args)
}