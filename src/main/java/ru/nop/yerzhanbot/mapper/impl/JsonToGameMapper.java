package ru.nop.yerzhanbot.mapper.impl;

import com.fasterxml.jackson.databind.JsonNode;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.data.Game;
import ru.nop.yerzhanbot.mapper.Mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JsonToGameMapper implements Mapper<JsonNode, Game> {

    public static final String APP_URL = "https://store.steampowered.com/app/%s";

    @Override
    public Game map(JsonNode source) {

        var game = new Game();
        var data = source.get("data");
        if (data == null) {
            return game;
        }

        String appid = String.valueOf(data.get("steam_appid").intValue());
        if (StringUtil.isNullOrEmpty(appid)) {
            log.error("No appId to map");
            return null;
        }

        game.setId(appid);
        game.setUrl(String.format(APP_URL, appid));
        game.setName(data.get("name").textValue());
        game.setImageUrl(data.get("header_image").textValue());

        var priceOverview = data.get("price_overview");
        if (priceOverview != null) {
            game.setPrice(priceOverview.get("final_formatted").textValue());
            game.setDiscountPercent(priceOverview.get("discount_percent").intValue());
        }

        var pcRequirements = data.get("pc_requirements");
        if (pcRequirements != null) {
            var minimum = pcRequirements.get("minimum").textValue();
            minimum = minimum.replace("\\", "").replace("t</li>", "");
            var strings = minimum.split("<br>");
            var formattedMinimumReq = Arrays.stream(strings)
                    .map(s -> Jsoup.parse(s, "").text())
                    .filter(s -> s.length() > 0)
                    .map(s -> s + "\n")
                    .collect(Collectors.joining());
            game.setMinimumRequirements(formattedMinimumReq);
        }
        return game;
    }
}
