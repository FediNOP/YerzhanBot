package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.BotFacade;

import java.util.List;

@Component
public class RemoveCommand implements Command {

    public static final String DESCRIPTION = "Удалить игру из списка отслеживаний";
    private final BotFacade botFacade;

    public RemoveCommand(BotFacade botFacade) {
        this.botFacade = botFacade;
    }

    @Override
    public void performCommand(Server server, TextChannel channel, String message) {
        channel.sendMessage(botFacade.removeGameFromChecklist(message));
    }

    @Override
    public List<String> getAliases() {
        return List.of("remove", "удоли", "удали", "выпили");
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
