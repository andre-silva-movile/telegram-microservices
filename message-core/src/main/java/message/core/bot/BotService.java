package message.core.bot;

import org.apache.commons.lang3.StringUtils;

public class BotService {

    private final BotRepository botRepository;

    public BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public Bot get(String botId) {
        if(StringUtils.isBlank(botId)) {
            throw new IllegalArgumentException("Unable to get bot without botId");
        }
        return botRepository.get(botId);
    }
}
