package ru.nop.yerzhanbot.service.impl;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.service.GameEmbedBuilderService;

import java.awt.*;

@Component
public class GameEmbedBuilderServiceImpl implements GameEmbedBuilderService {

    public static final String ID = "Ид";
    public static final String PRICE = "Цена";
    public static final String SELLOUT_PERCENT = "Процент скидки";
    public static final String NAME = "Название";

    @Override
    public EmbedBuilder createEmbedGame(Game game) {
        var embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(game.getName(), game.getUrl(), game.getImageUrl());
        embedBuilder.setTitle(game.getName());
        embedBuilder.setUrl(game.getUrl());
        embedBuilder.addField(ID, game.getId());
        embedBuilder.addField(PRICE, game.getPrice(), true);
        if (game.getDiscountPercent() > 0) {
            embedBuilder.setColor(Color.red);
            embedBuilder.addField(SELLOUT_PERCENT, game.getDiscountPercent() + "%", true);
        }
        embedBuilder.setImage(game.getImageUrl());
        return embedBuilder;
    }

    @Override
    public EmbedBuilder createListOfEmbedGames(Iterable<Game> gameList) {

        var embedBuilder = new EmbedBuilder();
        gameList.forEach(game -> {
            embedBuilder.addField(ID, game.getId(), true);
            embedBuilder.addField(NAME, game.getName());
        });
        return embedBuilder;
    }



}
