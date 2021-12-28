package ru.nop.yerzhanbot.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nop.yerzhanbot.data.NotifyChannel;

public interface NotifyChannelRepo extends MongoRepository<NotifyChannel, Long> {
}
