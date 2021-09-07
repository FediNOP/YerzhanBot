package ru.nop.yerzhanbot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.mapper.Mapper;
import ru.nop.yerzhanbot.service.StoreRequestService;

// TODO Надо сделать абстрактны класс
@Slf4j
@Service
public class SteamStoreRequestService implements StoreRequestService {

    public static final String APP_DETAILS = "https://store.steampowered.com/api/appdetails?appids=%s&cc=rub&l=ru";
    private final Mapper<JsonNode, Game> mapper;

    public SteamStoreRequestService(Mapper<JsonNode, Game> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Game findGame(String id) {

        var connection = getResponse(String.format(APP_DETAILS, id));
        log.info(connection.getStatusCode().toString());
        var body = getBody(connection);
        if (body == null) {
            log.warn("Game id {} request not found", id);
            return null;
        }

        return mapper.map(body.get(id));
    }

    private ResponseEntity<String> getResponse(String url) {
        return new RestTemplate().getForEntity(url, String.class);
    }

    private JsonNode getBody(ResponseEntity<String> response) {
        try {
            return new ObjectMapper().readTree(response.getBody());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
