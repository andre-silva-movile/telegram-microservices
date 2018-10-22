package message.router.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("message.router")
public class  MessageRouterApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MessageRouterApplication.class).run(args);
    }

}
