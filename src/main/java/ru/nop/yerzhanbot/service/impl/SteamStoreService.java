package ru.nop.yerzhanbot.service.impl;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.nop.yerzhanbot.bootstrap.BootStrapData;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.data.Setting;
import ru.nop.yerzhanbot.repo.GameRepo;
import ru.nop.yerzhanbot.repo.SettingRepo;
import ru.nop.yerzhanbot.service.GameEmbedBuilderService;
import ru.nop.yerzhanbot.service.StoreRequestService;
import ru.nop.yerzhanbot.service.StoreService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SteamStoreService implements StoreService {

    private final StoreRequestService storeRequestService;
    private final GameEmbedBuilderService gameEmbedBuilderService;
    private final GameRepo gameRepo;
    private final SettingRepo settingRepo;
    private TextChannel channel;

    public SteamStoreService(StoreRequestService storeRequestService, GameEmbedBuilderService gameEmbedBuilderService, GameRepo gameRepo, SettingRepo settingRepo) {
        this.storeRequestService = storeRequestService;
        this.gameEmbedBuilderService = gameEmbedBuilderService;
        this.gameRepo = gameRepo;
        this.settingRepo = settingRepo;
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
        return gameEmbedBuilderService.createEmbedGame(game).setFooter("Игра добавлена в отлеживание");
    }

    @Override
    public EmbedBuilder removeGameFromChecklist(String request) {
        var game = gameRepo.findById(request).orElse(null);
        if (game == null) {
            return new EmbedBuilder().setTitle("Игра не найдена");
        }
        gameRepo.delete(game);
        log.info("Game {} is deleted", game.toString());
        var embedGames = gameEmbedBuilderService.createListOfEmbedGames(List.of(game));
        embedGames.setTitle("Игра удалена");
        return embedGames;
    }

    @Override
    public void runCheckSellout() {
        if (channel == null) {
            log.warn("No channel set");
            return;
        }
        updateCheckListGameData();
        var notifyGames = getSelloutGames();
        if (CollectionUtils.isEmpty(notifyGames)) {
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
        var listOfEmbedGames = gameEmbedBuilderService.createListOfEmbedGames(gameRepo.findAll());
        listOfEmbedGames.setTitle("Список отслеживаемых игр");
        return listOfEmbedGames;
    }

    @Override
    public EmbedBuilder setNotifyChannel(TextChannel channel) {
        settingRepo.save(new Setting(BootStrapData.CHANNEL_ID, channel.getIdAsString()));
        log.info("New channel for notify {}", channel.getIdAsString());
        this.channel = channel;
        return new EmbedBuilder().setDescription("Крч сюда буду уведомления по скидкам кидать");
    }

    private List<Game> getSelloutGames() {
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
