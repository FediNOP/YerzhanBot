package ru.nop.yerzhanbot.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.mapper.Mapper;

class SteamStoreRequestServiceTest {


    SteamStoreRequestService steamStoreRequestService;
    @Mock
    Mapper<JsonNode, Game> mapper;

    @BeforeEach
    void setUp() {
        steamStoreRequestService = new SteamStoreRequestService(mapper);
    }

    @Test
    void Test() {
        steamStoreRequestService.findGame("218620");
    }

}