package ru.nop.yerzhanbot.mapper.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.mapper.Mapper;

@Component
@Slf4j
public class JsonToGameMapper implements Mapper<JsonNode, Game> {
    @Override
    public Game map(JsonNode source) {

        var game = new Game();
        var data = source.get("data");
        if (data == null) {
            return game;
        }

        game.setId(data.get("steam_appid").textValue());
        game.setName(data.get("name").textValue());
        game.setDescription(data.get("about_the_game").textValue());
        game.setImageUrl(data.get("header_image").textValue());

        var priceOverview = data.get("price_overview");
        if (priceOverview != null) {
            game.setPrice(priceOverview.get("final_formatted").textValue());
            game.setDiscountPercent(priceOverview.get("discount_percent").textValue());
        }
        return game;
    }
}
