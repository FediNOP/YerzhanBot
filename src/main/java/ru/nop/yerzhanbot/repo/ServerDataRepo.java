package ru.nop.yerzhanbot.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nop.yerzhanbot.data.ServerData;

public interface ServerDataRepo extends MongoRepository<ServerData, Long> {
}
