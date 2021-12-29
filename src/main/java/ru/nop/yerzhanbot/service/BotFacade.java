package ru.nop.yerzhanbot.service;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;

import java.util.List;

public interface BotFacade {

    EmbedBuilder addGameToCheckList(String request);

    EmbedBuilder removeGameFromChecklist(String request);

    List<EmbedBuilder> getSelloutGames();

    EmbedBuilder getCheckList();

    void setNotifyChannel(Server server, TextChannel channel);
}
