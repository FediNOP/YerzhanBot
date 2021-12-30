package ru.nop.yerzhanbot.data;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@Document
public class ServerData {

    @Id
    private Long id;

    private Long channelId;

    @DBRef
    private Set<Game> games;

    public ServerData(Long id) {
        this.id = id;
        games = new HashSet<>();
    }

}
