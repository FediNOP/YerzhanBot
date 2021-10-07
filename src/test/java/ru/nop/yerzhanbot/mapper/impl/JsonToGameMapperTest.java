package ru.nop.yerzhanbot.mapper.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class JsonToGameMapperTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("/test/testGameJson.json")
    Resource resourceFile;
    private JsonNode gameJsonNode;
    private JsonToGameMapper jsonToGameMapper;

    @Before
    public void setUp() throws IOException {
//        jsonToGameMapper = new JsonToGameMapper();
//        gameJsonNode = objectMapper.readValue(Objects.requireNonNull(getClass().getResource("/test/testGameJson.json")).getFile(), JsonNode.class);
    }


    @Test
    public void shouldEverythingParseOk() {

    }

}