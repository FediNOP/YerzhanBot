package ru.nop.yerzhanbot.facades.impl;

import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.channel.TextChannel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.data.ServerData;
import ru.nop.yerzhanbot.facades.GameSelloutService;
import ru.nop.yerzhanbot.repo.ServerDataRepo;
import ru.nop.yerzhanbot.service.GameService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DefaultGameSelloutService implements GameSelloutService {

    private final GameService gameService;
    private final ServerDataRepo serverDataRepo;

    public DefaultGameSelloutService(GameService gameService, ServerDataRepo serverDataRepo) {
        this.gameService = gameService;
        this.serverDataRepo = serverDataRepo;
    }

    @Override
    public void addGameToCheckList(Long serverId, Game game) {
        Assert.notNull(game, "Can't add game to check list! Game is null");
        if (!gameService.isGameSavedInRepo(game.getId())) {
            gameService.saveGame(game);
        }
        var serverData = serverDataRepo.findById(serverId).orElse(new ServerData(serverId));
        serverData.getGames().add(game);
        serverDataRepo.save(serverData);
    }

    @Override
    public void removeGameFromChecklist(Long serverId, Game game) {
        Assert.notNull(game, "Can't remove game from check list! Game is null!");
        final var serverData = serverDataRepo.findById(serverId).orElseThrow();
        serverData.getGames().remove(game);
        serverDataRepo.save(serverData);
    }

    @Override
    public boolean isGameInCheckList(Long serverId, Game game) {
        Assert.notNull(game, "Can't add game to check list! Game is null");
        final var serverData = serverDataRepo.findById(serverId).orElse(null);
        if (serverData == null) {
            log.warn("is Game In Check List, server id: {} not found", serverId);
            return false;
        }
        return serverData.getGames().stream()
                .anyMatch(gameFromSet -> Objects.equals(gameFromSet.getId(), game.getId()));
    }

    @Override
    public List<Game> getSelloutGames(Long serverId) {
        return getCheckList(serverId).stream()
                .filter(game -> game.getDiscountPercent() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> getCheckList(Long serverId) {
        final var serverData = serverDataRepo.findById(serverId).orElse(new ServerData(serverId));
        final var games = serverData.getGames();
        if (CollectionUtils.isEmpty(games)) {
            return List.of();
        }
        return List.copyOf(games);
    }

    @Override
    public void setNotifyChannel(Long serverId, TextChannel channel) {
        final var serverData = serverDataRepo.findById(serverId).orElse(new ServerData(serverId));
        serverData.setChannelId(channel.getId());
        serverDataRepo.save(serverData);
    }

}
