package ru.nop.yerzhanbot.service;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import ru.nop.yerzhanbot.data.Game;

public interface GameEmbedCreatorService {
    EmbedBuilder createGameEmbedBuilder(Game game);
}
