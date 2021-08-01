package ru.nop.yerzhanbot.service;

import ru.nop.yerzhanbot.data.Game;

import java.util.List;

public interface GameStoreService {

    Game findGameByName(String name);

    Game findGameById(String id);

    List<Game> findGamesByName(List<String> name);

    List<Game> findGamesById(List<String> id);

    void addGameToTrackList(String id);



}
