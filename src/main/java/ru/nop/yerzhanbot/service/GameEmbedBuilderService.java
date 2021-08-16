package ru.nop.yerzhanbot.service;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import ru.nop.yerzhanbot.data.Game;

public interface GameEmbedBuilderService {
    EmbedBuilder createEmbedGame(Game game);

    EmbedBuilder createListOfEmbedGames(Iterable<Game> gameList);

}
