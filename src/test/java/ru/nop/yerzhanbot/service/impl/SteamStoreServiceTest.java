package ru.nop.yerzhanbot.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.service.GameEmbedBuilderService;
import ru.nop.yerzhanbot.service.StoreRequestService;

import static org.junit.jupiter.api.Assertions.*;

class SteamStoreServiceTest {

    public static final String ID = "1426210";
    public static final String NAME = "Name";
    @Mock
    private GameEmbedBuilderService embedBuilderService;

    @Mock
    private StoreRequestService requestService;

    private Game game;

    @InjectMocks
    private SteamStoreService storeService;

    @BeforeEach
    void setUp() {

        game = new Game();
        game.setId(ID);
        game.setName(NAME);

    }

    @Test
    void shouldAddGameToCheckList(){
//        storeService.addGameToCheckLit(ID);
    }

}