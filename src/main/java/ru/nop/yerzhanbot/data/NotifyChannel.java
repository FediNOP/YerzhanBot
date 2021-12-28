package ru.nop.yerzhanbot.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class NotifyChannel {

    @Id
    private Long serverId;

    private Long channelId;

}
