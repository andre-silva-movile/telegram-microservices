package message.processor.reverse.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("message.processor.reverse")
public class MessageProcessorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MessageProcessorApplication.class).run(args);
    }

}
