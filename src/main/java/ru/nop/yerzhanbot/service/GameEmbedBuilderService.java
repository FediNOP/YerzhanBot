package ru.nop.yerzhanbot.service;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import ru.nop.yerzhanbot.data.Game;

import java.util.List;

public interface GameEmbedBuilderService {
    EmbedBuilder createEmbedGame(Game game);

    EmbedBuilder createListOfEmbedGames(List<Game> gameList);

}
