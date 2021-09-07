package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.BotFacade;

import java.util.List;

@Component
public class SetNotifyChannelCommand implements Command {

    public static final String DESCRIPTION = "Получить помощь";
    private final BotFacade botFacade;

    public SetNotifyChannelCommand(BotFacade botFacade) {
        this.botFacade = botFacade;
    }

    @Override
    public void performCommand(TextChannel channel, String message) {
        channel.sendMessage(botFacade.setNotifyChannel(channel));
    }

    @Override
    public List<String> getAliases() {
        return List.of("thisChannel", "сюда скидки кидай");
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
