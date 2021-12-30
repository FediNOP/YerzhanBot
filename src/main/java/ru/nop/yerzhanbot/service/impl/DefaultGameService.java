package ru.nop.yerzhanbot.service.impl;

import io.netty.util.internal.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.repo.GameRepo;
import ru.nop.yerzhanbot.service.GameService;
import ru.nop.yerzhanbot.service.StoreRequestService;

@Log4j2
@Component
public class DefaultGameService implements GameService {

    //TODO Должен выбрать магазин перед запросом
    private final StoreRequestService storeRequestService;
    private final GameRepo gameRepo;

    public DefaultGameService(StoreRequestService storeRequestService, GameRepo gameRepo) {
        this.storeRequestService = storeRequestService;
        this.gameRepo = gameRepo;
    }

    @Override
    public Game getGameForId(String id) {
        final var gameFromCache = getGameFromRepo(id);
        return gameFromCache != null ? gameFromCache : getGameFromStore(id);
    }

    @Override
    public Game getGameFromRepo(String id) {
        if (StringUtil.isNullOrEmpty(id)) {
            throw new IllegalArgumentException("Id is null");
        }
        return gameRepo.findById(id).orElse(null);
    }

    @Override
    public Game getGameFromStore(String gameId) {
        return storeRequestService.findGame(gameId);
    }

    @Override
    public void saveGame(Game game) {
        Assert.notNull(game, "Game is null");
        gameRepo.save(game);
    }

    @Override
    public void removeGame(Game game) {
        Assert.notNull(game, "Game is null");
        gameRepo.delete(game);
    }

    @Override
    public boolean isGameSavedInRepo(String id) {
        return getGameFromRepo(id) != null;
    }

    @Override
    public void updateGameInfo(String id) {
        if (!isGameSavedInRepo(id)) {
            return;
        }
        final var gameFromStore = getGameFromStore(id);
        if (gameFromStore != null) {
            gameRepo.save(gameFromStore);
        }
    }

    @Override
    public void updateAllGamesInfos() {

    }

}
