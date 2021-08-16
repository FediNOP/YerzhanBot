package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.StoreService;

import java.util.List;

@Component
public class AddCommand implements Command {

    public static final String DESCRIPTION = "Добавить игру в список отслеживаемых игр";
    private final StoreService storeService;

    public AddCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void performCommand(TextChannel channel, String message) {
        channel.sendMessage(storeService.addGameToCheckList(message));
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
