package ru.nop.yerzhanbot.service;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public interface StoreService {

    EmbedBuilder addGameToCheckList(String request);

    EmbedBuilder removeGameFromChecklist(String request);

    EmbedBuilder setNotifyChannel(TextChannel channel);

    void runCheckSellout();

    EmbedBuilder getCheckList();

}
