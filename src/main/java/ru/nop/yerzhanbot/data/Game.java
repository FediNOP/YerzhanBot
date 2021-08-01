package ru.nop.yerzhanbot.data;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Game {

    private String id;

    private String name;

    private String description;

    private String url;

    private String imageUrl;

    private Float selloutPrice;

    private Float price;

}
