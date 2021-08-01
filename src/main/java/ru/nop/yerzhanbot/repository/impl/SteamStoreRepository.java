package ru.nop.yerzhanbot.repository.impl;

import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.data.User;
import ru.nop.yerzhanbot.repository.StoreRepository;

import java.util.List;

@Component
public class SteamStoreRepository implements StoreRepository {

    @Override
    public Game getFirstGame(String name){
        return null;
    }

    @Override
    public List<Game> findAllGames(String name){
        return null;
    }

    @Override
    public User getFirstUser(String name){
        return null;
    }

}
