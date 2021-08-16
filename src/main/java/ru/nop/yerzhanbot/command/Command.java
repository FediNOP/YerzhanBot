package ru.nop.yerzhanbot.command;

import org.javacord.api.entity.channel.TextChannel;

import java.util.List;

public interface Command {
    void performCommand(TextChannel channel, String message);

    List<String> getAliases();

    String getDescription();
}
