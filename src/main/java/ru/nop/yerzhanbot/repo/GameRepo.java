package ru.nop.yerzhanbot.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nop.yerzhanbot.data.Game;

public interface GameRepo extends MongoRepository<Game, String> {
}
