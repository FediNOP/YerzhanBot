package ru.nop.yerzhanbot.facades.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.data.ServerData;
import ru.nop.yerzhanbot.facades.GameSelloutService;
import ru.nop.yerzhanbot.repo.ServerDataRepo;
import ru.nop.yerzhanbot.service.GameService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        var serverData = serverDataRepo.findById(serverId).orElse(null);
        if (serverData == null) {
            serverData = new ServerData();
            serverData.setId(serverId);
            serverData.setGames(Set.of(game));

        } else {
            serverData.getGames()
        }

    }

    @Override
    public void removeGameFromChecklist(Long serverId, Game game) {

    }

    @Override
    public boolean isGameInCheckList(Long serverId, Game game) {
        return false;
    }

    @Override
    public List<Game> getSelloutGames(Long serverId) {
        return getCheckList(serverId).stream()
                .filter(game -> game.getDiscountPercent() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> getCheckList(Long serverId) {
        final var serverData = serverDataRepo.findById(serverId).orElse(new ServerData());
        final var games = serverData.getGames();
        if (CollectionUtils.isEmpty(games)) {
            return List.of();
        }
        return List.copyOf(games);
    }

    @Override
    public void setNotifyChannel(Long serverId, TextChannel channel) {

    }

}
