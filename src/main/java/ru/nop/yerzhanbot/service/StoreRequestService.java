package ru.nop.yerzhanbot.service;

import ru.nop.yerzhanbot.data.Game;

public interface StoreRequestService {
    Game findGame(String id);
}
