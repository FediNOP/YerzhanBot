package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.StoreService;

import java.util.List;

@Component
public class GetCheckListCommand implements Command {

    public static final String DESCRIPTION = "Получить спсисок отслеживаемых игр";
    private final StoreService storeService;

    public GetCheckListCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void performCommand(TextChannel channel, String message) {
        channel.sendMessage(storeService.getCheckList());
    }

    @Override
    public List<String> getAliases() {
        return List.of("getchecklist", "список", "игоры");
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
