package ru.nop.yerzhanbot.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.data.NotifyChannel;
import ru.nop.yerzhanbot.service.BotFacade;
import ru.nop.yerzhanbot.service.EmbedGameService;
import ru.nop.yerzhanbot.service.GameService;
import ru.nop.yerzhanbot.service.ServerDataService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BotFacadeImpl implements BotFacade {

    private final EmbedGameService embedGameService;
    private final GameService gameService;
    private final ServerDataService serverDataService;

    public BotFacadeImpl(EmbedGameService embedGameService, GameService gameService, ServerDataService serverDataService) {
        this.embedGameService = embedGameService;
        this.gameService = gameService;
        this.serverDataService = serverDataService;
    }

    @Override
    public EmbedBuilder addGameToCheckList(String request) {

        final var gameFromCache = gameService.getGameFromRepo(request);
        if (gameFromCache != null) {
            return new EmbedBuilder().setTitle(gameFromCache.getName()).setDescription("Игра уже добавлена");
        }

        final var gameFromStore = gameService.getGameFromStore(request);
        if (gameFromStore == null) {
            return new EmbedBuilder().setTitle("Игра не найдена");
        }

        gameService.saveGame(gameFromStore);
        log.info("Game {} is added to check list", gameFromStore.getId());
        return embedGameService.createEmbedGame(gameFromStore).setFooter("Игра добавлена в отcлеживание");
    }

    @Override
    public EmbedBuilder removeGameFromChecklist(String request) {
        final var gameFromRepo = gameService.getGameFromRepo(request);
        if (gameFromRepo == null) {
            return new EmbedBuilder().setTitle("Игра не в списке отсле");
        }
        gameService.removeGame(gameFromRepo);
        log.info("Game {} is deleted", gameFromRepo);
        var embedGames = embedGameService.createListOfEmbedGames(List.of(gameFromRepo));
        embedGames.setTitle("Игра удалена");
        return embedGames;
    }

    @Override
    public List<EmbedBuilder> getSelloutGames() {

        updateCheckListGameData();
        var notifyGames = checkSellout();
        if (CollectionUtils.isEmpty(notifyGames)) {
            log.info("No sellout game found");
            return List.of();
        }
        return notifyGames.stream()
                .map(embedGameService::createEmbedGame)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public EmbedBuilder getCheckList() {
        var listOfEmbedGames = embedGameService.createListOfEmbedGames(gameRepo.findAll());
        listOfEmbedGames.setTitle("Список отслеживаемых игр");
        return listOfEmbedGames;
    }

    @Override
    public void setNotifyChannel(@NonNull Server server, @NonNull TextChannel channel) {
        notifyChannelRepo.save(new NotifyChannel(server.getId(), channel.getId()));
        log.info("New notify channel for server {} and channel {}", server.getIdAsString(), channel.getIdAsString());
    }

    public List<Game> checkSellout() {
        return gameRepo.findAll().stream()
                .filter(game -> game.getDiscountPercent() > 0)
                .collect(Collectors.toList());
    }

    private void updateCheckListGameData() {
        gameRepo.findAll().stream()
                .map(game -> storeRequestService.findGame(game.getId()))
                .forEach(gameRepo::save);
    }

}
