package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.BotFacade;

import java.util.List;

@Component
public class GetSelloutGamesCommand implements Command {

    public static final String DESCRIPTION = "Получить скидки";
    private final BotFacade botFacade;

    public GetSelloutGamesCommand(BotFacade botFacade) {
        this.botFacade = botFacade;
    }

    @Override
    public void performCommand(Server server, TextChannel channel, String message) {
        var selloutGames = botFacade.getSelloutGames();
        if (selloutGames.isEmpty())
            channel.sendMessage("Сегодня нет скидок :(");
        else
            selloutGames.forEach(channel::sendMessage);
    }

    @Override
    public List<String> getAliases() {
        return List.of("checksellout", "скидки", "скидОчки", "шо там со скидками");
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
