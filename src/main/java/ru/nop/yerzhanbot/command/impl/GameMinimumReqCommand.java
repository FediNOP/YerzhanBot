package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.BotFacade;

import java.util.List;

@Component
public class GameMinimumReqCommand implements Command {

    private final BotFacade botFacade;

    public GameMinimumReqCommand(BotFacade botFacade) {
        this.botFacade = botFacade;
    }

    @Override
    public void performCommand(Server server, TextChannel channel, String message) {
        channel.sendMessage(botFacade.getGameMinimumRequirements(message));
    }

    @Override
    public List<String> getAliases() {
        return List.of("minReq", "дай минимальные требования на эту игру", "дай мин треб");
    }

    @Override
    public String getDescription() {
        return "Получить минимальные требования для игры";
    }
}
