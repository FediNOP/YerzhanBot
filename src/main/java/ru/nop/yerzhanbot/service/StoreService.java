package ru.nop.yerzhanbot.service;

import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public interface StoreService {

    EmbedBuilder addGameToCheckLit(String request);

    void setNotifyChannel(ServerTextChannel channel);

    void runCheckSellout();

    EmbedBuilder getCheckList();

}
