package ru.nop.yerzhanbot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.service.GameEmbedBuilderService;
import ru.nop.yerzhanbot.service.StoreRequestService;
import ru.nop.yerzhanbot.service.StoreService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SteamStoreService implements StoreService {

    private final StoreRequestService storeRequestService;
    private final GameEmbedBuilderService gameEmbedBuilderService;
    private final Map<String, Game> checkList;

    public SteamStoreService(StoreRequestService storeRequestService, GameEmbedBuilderService gameEmbedBuilderService) {
        this.storeRequestService = storeRequestService;
        this.gameEmbedBuilderService = gameEmbedBuilderService;
        checkList = new HashMap<>();
    }

    @Override
    public EmbedBuilder addGameToCheckLit(String request) {

        var game = storeRequestService.findGame(request);
        if (game == null) {
            return new EmbedBuilder().setTitle("Игра не найдена");
        }
        checkList.put(game.getId(), game);
        log.info("Game {} is added to check list", game.getId());
        return gameEmbedBuilderService.createGameEmbedBuilder(game);
    }
}
