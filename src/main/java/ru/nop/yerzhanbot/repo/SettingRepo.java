package ru.nop.yerzhanbot.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nop.yerzhanbot.data.Setting;

public interface SettingRepo extends MongoRepository<Setting, String> {
}
