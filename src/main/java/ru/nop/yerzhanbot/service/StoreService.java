package ru.nop.yerzhanbot.service;

import org.javacord.api.entity.message.embed.EmbedBuilder;

public interface StoreService {

    EmbedBuilder addGameToCheckLit(String request);

}
