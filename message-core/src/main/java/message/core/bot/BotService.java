package message.core.bot;

import org.apache.commons.lang3.StringUtils;

public class BotService {

    private final BotRepository botRepository;

    public BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public Bot get(String botId) {
        if (StringUtils.isBlank(botId)) {
            throw new IllegalArgumentException("Unable to get bot without botId");
        }
        return botRepository.get(botId);
    }

    public String set(String botId, Bot bot) {
        if (StringUtils.isBlank(botId) || bot == null) {
            throw new IllegalArgumentException("Unable to set bot value without botId");
        }
        return botRepository.set(botId, bot);

    }
}
