package ru.nop.yerzhanbot.data;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@ToString
@Document
public class Game {

    @Id
    private String id;

    private String name;

    private String url;

    private String imageUrl;

    private Integer discountPercent;

    private String price;

    public Game() {
        this.id = "";
        this.name = "";
        this.url = "";
        this.imageUrl = "";
        this.discountPercent = 0;
        this.price = "";
    }

}
