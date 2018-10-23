package message.core.bot;

import java.io.Serializable;

public class Bot implements Serializable {

    private static final long serialVersionUID = 7637557111817969507L;
    private String name;
    private String token;
    private String processor;

    private Bot() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public String toString() {
        return "Bot{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", processor='" + processor + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private String token;
        private String processor;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setProcessor(String processor) {
            this.processor = processor;
            return this;
        }

        public Bot build() {
            Bot bot = new Bot();
            bot.setName(name);
            bot.setProcessor(processor);
            bot.setToken(token);
            return bot;
        }
    }
}
