package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.StoreService;

import java.util.List;

@Component
public class RemoveCommand implements Command {

    public static final String DESCRIPTION = "Удалить игру из списка отслеживаний";
    private final StoreService storeService;

    public RemoveCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void performCommand(TextChannel channel, String message) {
        channel.sendMessage(storeService.removeGameFromChecklist(message));
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
