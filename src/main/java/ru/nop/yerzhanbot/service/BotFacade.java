package ru.nop.yerzhanbot.service;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;

import java.util.List;

public interface BotFacade {

    MessageBuilder addGameToCheckList(String request);

    EmbedBuilder removeGameFromChecklist(String request);

    List<EmbedBuilder> getSelloutGames();

    void setNotifyChannel(Server server, TextChannel channel);

    EmbedBuilder getCheckList();

    String getGameMinimumRequirements(String request);

}
