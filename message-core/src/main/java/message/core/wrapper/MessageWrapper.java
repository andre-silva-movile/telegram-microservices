package message.core.wrapper;

import message.core.telegram.Message;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageWrapper implements Serializable {

    private static final long serialVersionUID = 7764295023112623709L;
    private Long bot;
    private Integer updateId;
    private Message message;
    private List<String> response;

    public static Builder builder() {
        return new Builder();
    }

    public Long getBot() {
        return bot;
    }

    public void setBot(Long bot) {
        this.bot = bot;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public void addResponse(String response) {
        if (CollectionUtils.isEmpty(this.response)) {
            this.response = new ArrayList<>();
        }
        this.response.add(response);
    }

    @Override
    public String toString() {
        return "MessageWrapper{" +
                "bot=" + bot +
                ", updateId=" + updateId +
                ", message=" + message +
                '}';
    }

    public static class Builder {

        private Long bot;
        private Integer updateId;
        private Message message;
        private List<String> response;

        private Builder() {
            response = new ArrayList<>();
        }

        public Builder setUpdateId(Integer updateId) {
            this.updateId = updateId;
            return this;
        }


        public Builder setBot(Long bot) {
            this.bot = bot;
            return this;
        }

        public Builder setMessage(Message message) {
            this.message = message;
            return this;
        }


        public MessageWrapper build() {
            MessageWrapper messageWrapper = new MessageWrapper();
            messageWrapper.setBot(bot);
            messageWrapper.setMessage(message);
            messageWrapper.setUpdateId(updateId);
            messageWrapper.setResponse(response);
            return messageWrapper;
        }

    }
}
