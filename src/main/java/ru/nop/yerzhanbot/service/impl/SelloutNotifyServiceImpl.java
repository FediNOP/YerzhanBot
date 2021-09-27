package ru.nop.yerzhanbot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.repo.NotifyChannelRepo;
import ru.nop.yerzhanbot.service.BotFacade;
import ru.nop.yerzhanbot.service.SelloutNotifyService;

@Slf4j
@Component
public class SelloutNotifyServiceImpl implements SelloutNotifyService {

    private final BotFacade botFacade;
    private final DiscordApi discordApi;
    private final NotifyChannelRepo notifyChannelRepo;

    public SelloutNotifyServiceImpl(BotFacade botFacade, DiscordApi discordApi, NotifyChannelRepo notifyChannelRepo) {
        this.botFacade = botFacade;
        this.notifyChannelRepo = notifyChannelRepo;
        this.discordApi = discordApi;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    @Override
    public void run() {
        log.info("Running check sellout task");

        var selloutGames = botFacade.getSelloutGames();
        log.info("Find {} sellout games", selloutGames.size());
        var notifyChannels = notifyChannelRepo.findAll();
        log.info("Find {} channels for notify", notifyChannels.size());
        notifyChannels.forEach(notifyChannel -> discordApi.getTextChannelById(notifyChannel.getChannelId())
                .ifPresent(textChannel -> selloutGames.forEach(textChannel::sendMessage)));
    }

}
