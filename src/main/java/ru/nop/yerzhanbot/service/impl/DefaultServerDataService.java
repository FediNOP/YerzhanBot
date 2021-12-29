package ru.nop.yerzhanbot.service.impl;

import ru.nop.yerzhanbot.data.ServerData;
import ru.nop.yerzhanbot.service.ServerDataService;

public class DefaultServerDataService implements ServerDataService {
    @Override
    public ServerData getServerData(Long serverId) {
        return null;
    }

    @Override
    public void saveServerData(ServerData serverData) {

    }

    @Override
    public ServerData removeServerData(Long serverId) {
        return null;
    }

    @Override
    public void setNotifyChannel(Long serverId, Long channelId) {

    }
}
