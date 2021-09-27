package ru.nop.yerzhanbot.command;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;

import java.util.List;

public interface Command {
    void performCommand(Server server, TextChannel channel, String message);

    List<String> getAliases();

    String getDescription();
}
