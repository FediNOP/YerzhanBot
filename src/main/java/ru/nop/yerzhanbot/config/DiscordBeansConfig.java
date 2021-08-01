package ru.nop.yerzhanbot.config;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordBeansConfig {

    @Bean
    public EmbedBuilder embedBuilder(){
        return new EmbedBuilder();
    }

}
