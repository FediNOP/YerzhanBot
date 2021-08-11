package ru.nop.yerzhanbot.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.repo.SettingRepo;
import ru.nop.yerzhanbot.service.StoreService;

@Slf4j
@Component
public class BootStrapData implements CommandLineRunner {

    public static final String CHANNEL_ID = "channel_id";
    private final StoreService storeService;
    private final SettingRepo settingRepo;
    private final DiscordApi discordApi;

    public BootStrapData(StoreService storeService, SettingRepo settingRepo, DiscordApi discordApi) {
        this.storeService = storeService;
        this.settingRepo = settingRepo;
        this.discordApi = discordApi;
    }

    @Override
    public void run(String... args) throws Exception {
        setChannelToNotify();
    }

    private void setChannelToNotify() {
        var channelIdSetting = settingRepo.findById(CHANNEL_ID);
        if (channelIdSetting.isEmpty()) {
            log.info("Notify channel not found");
            return;
        }
        var channelId = channelIdSetting.get().getValue();
        var textChannelById = discordApi.getServerTextChannelById(channelId);
        textChannelById.ifPresent(storeService::setNotifyChannel);
    }
}
