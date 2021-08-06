package ru.nop.yerzhanbot.config;

import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Slf4j
@Configuration
public class BotSetupConfig {

    private final Environment environment;
    private final List<CommandExecutor> commandExecutors;

    public BotSetupConfig(Environment environment, List<CommandExecutor> commandExecutors) {
        this.environment = environment;
        this.commandExecutors = commandExecutors;
    }

    @Bean
    @ConfigurationProperties("discord-api")
    public DiscordApi discordApi() {
        log.info("Initialising api");
        String token = environment.getProperty("TOKEN");
        log.info(token);
        var discordApi = new DiscordApiBuilder().setToken(token).login().join();
        var javacordHandler = new JavacordHandler(discordApi);
        commandExecutors.forEach(javacordHandler::registerCommand);
        javacordHandler.getCommands().forEach(command -> log.info(command.toString()));
        return discordApi;
    }

}
