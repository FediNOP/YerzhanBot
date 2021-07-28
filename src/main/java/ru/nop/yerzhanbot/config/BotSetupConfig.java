package ru.nop.yerzhanbot.config;

import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
public class BotSetupConfig {

    private final Environment environment;

    public BotSetupConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @ConfigurationProperties("discord-api")
    public DiscordApi discordApi() {
        log.info("Initialising api");
        String token = environment.getProperty("TOKEN");
        log.info(token);
        var discordApi = new DiscordApiBuilder().setToken(token).login().join();
        log.info("Initialising listeners");
        discordApi.addMessageCreateListener(messageCreateEvent -> messageCreateEvent.getChannel().sendMessage("Ok"));
        return discordApi;
    }

}
