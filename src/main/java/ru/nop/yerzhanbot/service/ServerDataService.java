package ru.nop.yerzhanbot.service;

import org.springframework.lang.NonNull;
import ru.nop.yerzhanbot.data.ServerData;

public interface ServerDataService {

    ServerData getServerData(@NonNull Long serverId);

    void saveServerData(ServerData serverData);

    ServerData removeServerData(@NonNull Long serverId);

    void setNotifyChannel(@NonNull Long serverId, @NonNull Long channelId);

}
