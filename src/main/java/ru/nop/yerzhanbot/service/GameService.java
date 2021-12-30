package ru.nop.yerzhanbot.service;

import org.springframework.lang.NonNull;
import ru.nop.yerzhanbot.data.Game;

public interface GameService {

    Game getGameForId(@NonNull String id);

    Game getGameFromRepo(@NonNull String id);

    Game getGameFromStore(@NonNull String gameId);

    void saveGame(@NonNull Game game);

    void removeGame(@NonNull Game gameId);

    boolean isGameSavedInRepo(String id);

    void updateGameInfo(String id);

    void updateAllGamesInfos();

}
