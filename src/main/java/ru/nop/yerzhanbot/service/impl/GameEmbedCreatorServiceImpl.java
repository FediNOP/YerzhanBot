package ru.nop.yerzhanbot.service.impl;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.service.GameEmbedCreatorService;

@Component
public class GameEmbedCreatorServiceImpl implements GameEmbedCreatorService {

    @Override
    public EmbedBuilder createGameEmbedBuilder(Game game) {
        var embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(game.getName(), game.getUrl(), game.getImageUrl());
        embedBuilder.setUrl(game.getUrl());
        embedBuilder.setDescription(game.getDescription());
        embedBuilder.addField("Price", game.getPrice().toString());
        embedBuilder.addField("Id", game.getId());
        embedBuilder.setImage(game.getImageUrl());
        return embedBuilder;
    }
}
