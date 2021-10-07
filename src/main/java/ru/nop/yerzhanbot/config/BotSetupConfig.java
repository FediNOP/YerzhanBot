package ru.nop.yerzhanbot.config;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.nop.yerzhanbot.listeners.GameCommandsListener;
import ru.nop.yerzhanbot.listeners.GameReactionListener;

@Slf4j
@Configuration
public class BotSetupConfig {

    private final Environment environment;
    private final GameCommandsListener gameCommandsListener;
    private final GameReactionListener gameReactionListener;

    public BotSetupConfig(Environment environment, GameCommandsListener gameCommandsListener, GameReactionListener gameReactionListener) {
        this.environment = environment;
        this.gameCommandsListener = gameCommandsListener;
        this.gameReactionListener = gameReactionListener;
    }

    @Bean
    public DiscordApi discordApi() {
        log.info("Initialising api");
        String token = environment.getProperty("TOKEN");
        if (StringUtil.isNullOrEmpty(token)) {
            log.error("No token found!");
        }
        var discordApi = new DiscordApiBuilder().setToken(token).login().join();
        discordApi.addListener(gameCommandsListener);
        discordApi.addListener(gameReactionListener);
        discordApi.updateActivity(ActivityType.PLAYING, "Ержан помоги");
        return discordApi;
    }

}
