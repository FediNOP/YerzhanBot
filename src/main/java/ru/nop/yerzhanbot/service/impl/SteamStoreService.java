package ru.nop.yerzhanbot.service.impl;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.service.GameEmbedBuilderService;
import ru.nop.yerzhanbot.service.StoreRequestService;
import ru.nop.yerzhanbot.service.StoreService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SteamStoreService implements StoreService {

    private final StoreRequestService storeRequestService;
    private final GameEmbedBuilderService gameEmbedBuilderService;
    private final Map<String, Game> checkIdList;
    private ServerTextChannel channel;

    public SteamStoreService(StoreRequestService storeRequestService, GameEmbedBuilderService gameEmbedBuilderService) {
        this.storeRequestService = storeRequestService;
        this.gameEmbedBuilderService = gameEmbedBuilderService;
        checkIdList = new HashMap<>();
    }

    @Override
    public EmbedBuilder addGameToCheckLit(String request) {

        var game = storeRequestService.findGame(request);
        if (game == null || StringUtil.isNullOrEmpty(game.getId())) {
            return new EmbedBuilder().setTitle("Игра не найдена");
        }

        if(checkIdList.containsKey(game.getId())){
            return new EmbedBuilder().setTitle("Игра уже добавлена");
        }

        checkIdList.put(game.getId(), game);
        log.info("Game {} is added to check list", game.getId());
        return gameEmbedBuilderService.createEmbedGame(game).setFooter("Игра добавлена в отлеживание");
    }

    @Override
    public void runCheckSellout() {

        if (channel == null) {
            log.warn("No channel set");
            return;
        }

        var notifyGames = getNotifyGames();
        if (notifyGames.isEmpty()) {
            channel.sendMessage("Сегодня нет скидок :(");
            log.info("No sellout game found");
            return;
        }
        channel.sendMessage("Скидки подъехали");
        notifyGames.stream()
                .map(gameEmbedBuilderService::createEmbedGame)
                .filter(Objects::nonNull)
                .forEach(channel::sendMessage);

    }

    @Override
    public EmbedBuilder getCheckList() {
        var listOfEmbedGames = gameEmbedBuilderService.createListOfEmbedGames(new ArrayList<>(checkIdList.values()));
        listOfEmbedGames.setTitle("Список отслеживаемых игр");
        return listOfEmbedGames;
    }

    private List<Game> getNotifyGames() {
        return checkIdList.keySet().stream()
                .map(storeRequestService::findGame)
                .filter(Objects::nonNull)
                .filter(game -> game.getDiscountPercent() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public void setNotifyChannel(ServerTextChannel channel) {
        log.info("New channel for notify {}", channel.getName());
        this.channel = channel;
    }

}
