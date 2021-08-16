package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.StoreService;

import java.util.List;

@Component
public class SetNotifyChannelCommand implements Command {

    public static final String DESCRIPTION = "Получить помощь";
    private final StoreService storeService;

    public SetNotifyChannelCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void performCommand(TextChannel channel, String message) {
        channel.sendMessage(storeService.setNotifyChannel(channel));
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
