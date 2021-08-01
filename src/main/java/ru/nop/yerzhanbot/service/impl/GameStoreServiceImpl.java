package ru.nop.yerzhanbot.service.impl;

import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.repository.StoreRepository;
import ru.nop.yerzhanbot.service.GameStoreService;

import java.util.List;

@Component
public class GameStoreServiceImpl implements GameStoreService {

    private final StoreRepository storeRepository;

    public GameStoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Game findGameByName(String name) {
        return null;
    }

    @Override
    public Game findGameById(String id) {
        return null;
    }

    @Override
    public List<Game> findGamesByName(List<String> name) {
        return null;
    }

    @Override
    public List<Game> findGamesById(List<String> id) {
        return null;
    }

    @Override
    public void addGameToTrackList(String id) {

    }
}
