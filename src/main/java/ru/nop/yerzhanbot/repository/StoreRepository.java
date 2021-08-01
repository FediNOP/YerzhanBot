package ru.nop.yerzhanbot.repository;

import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.data.User;

import java.util.List;

public interface StoreRepository {

    Game getFirstGame(String name);

    List<Game> findAllGames(String name);

    User getFirstUser(String name);
}
