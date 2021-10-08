package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.BotFacade;

import java.util.List;

@Component
public class AddCommand implements Command {

    public static final String DESCRIPTION = "Добавить игру в список отслеживаемых игр";
    private final BotFacade botFacade;

    public AddCommand(BotFacade botFacade) {
        this.botFacade = botFacade;
    }

    @Override
    public void performCommand(Server server, TextChannel channel, String message) {
        var messageBuilder = botFacade.addGameToCheckList(message);
        messageBuilder.send(channel);
    }

    @Override
    public List<String> getAliases() {
        return List.of("add", "добавь", "запили");
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
