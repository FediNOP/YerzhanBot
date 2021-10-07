package ru.nop.yerzhanbot.service.impl;

import io.netty.util.internal.StringUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.data.NotifyChannel;
import ru.nop.yerzhanbot.repo.GameRepo;
import ru.nop.yerzhanbot.repo.NotifyChannelRepo;
import ru.nop.yerzhanbot.service.BotFacade;
import ru.nop.yerzhanbot.service.EmbedGameService;
import ru.nop.yerzhanbot.service.StoreRequestService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//TODO Должна отличать серверы и в будущем магазины
@Slf4j
@Component
public class BotFacadeImpl implements BotFacade {

    private final StoreRequestService storeRequestService;
    private final EmbedGameService embedGameService;
    private final GameRepo gameRepo;
    private final NotifyChannelRepo notifyChannelRepo;

    public BotFacadeImpl(StoreRequestService storeRequestService, EmbedGameService embedGameService, GameRepo gameRepo, NotifyChannelRepo notifyChannelRepo) {
        this.storeRequestService = storeRequestService;
        this.embedGameService = embedGameService;
        this.gameRepo = gameRepo;
        this.notifyChannelRepo = notifyChannelRepo;
    }

    @Override
    public EmbedBuilder addGameToCheckList(String request) {
        var game = storeRequestService.findGame(request);
        if (game == null || StringUtil.isNullOrEmpty(game.getId())) {
            return new EmbedBuilder().setTitle("Игра не найдена");
        }
        if (gameRepo.existsById(game.getId())) {
            return new EmbedBuilder().setTitle(game.getName()).setDescription("Игра уже добавлена");
        }
        gameRepo.save(game);
        log.info("Game {} is added to check list", game.getId());
        return embedGameService.createEmbedGame(game).setFooter("Игра добавлена в отcлеживание");
    }

    @Override
    public EmbedBuilder removeGameFromChecklist(String request) {
        var game = gameRepo.findById(request).orElse(null);
        if (game == null) {
            return new EmbedBuilder().setTitle("Игра не найдена");
        }
        gameRepo.delete(game);
        log.info("Game {} is deleted", game);
        var embedGames = embedGameService.createListOfEmbedGames(List.of(game));
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

    @Override
    public String getGameMinimumRequirements(String request) {
        var game = gameRepo.findById(request).orElse(storeRequestService.findGame(request));
        return game != null ? game.getMinimumRequirements() : "Не нашел мин требования для этой игры";
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
