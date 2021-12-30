package ru.nop.yerzhanbot.facades;

import org.javacord.api.entity.channel.TextChannel;
import org.springframework.lang.NonNull;
import ru.nop.yerzhanbot.data.Game;

import java.util.List;

public interface GameSelloutService {

    void addGameToCheckList(Long serverId, @NonNull Game game);

    void removeGameFromChecklist(Long serverId, @NonNull Game game);

    boolean isGameInCheckList(Long serverId, @NonNull Game game);

    List<Game> getSelloutGames(Long serverId);

    List<Game> getCheckList(Long serverId);

    void setNotifyChannel(Long serverId, TextChannel channel);

}
