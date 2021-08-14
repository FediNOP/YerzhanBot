package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.service.StoreService;

import java.util.List;

@Component
public class GetSelloutGamesCommand implements Command {

    public static final String DESCRIPTION = "Получить скидки";
    private final StoreService storeService;

    public GetSelloutGamesCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void performCommand(TextChannel channel, String message) {
        storeService.runCheckSellout();
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
