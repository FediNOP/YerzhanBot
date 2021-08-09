package ru.nop.yerzhanbot.command;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.Message;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.service.StoreService;

@Slf4j
@Component
public class GameSystemCommand implements CommandExecutor {

    public static final String ADD = "!add";
    private final StoreService storeService;

    public GameSystemCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Command(aliases = ADD, description = "Add game")
    public void addGame(ServerTextChannel channel, Message message) {
        log.info(message.getContent());
        if (message.getContent().isEmpty()) {
            return;
        }
        String request = message.getContent().replace(ADD, "").trim();
        channel.sendMessage(storeService.addGameToCheckLit(request));
    }

    @Command(aliases = "!thisChannel", description = "Set notify channel")
    public void setNotifyChannel(ServerTextChannel channel){
        storeService.setNotifyChannel(channel);
        channel.sendMessage("Канал для уведомлений успешно изменен");
    }

    @Command(aliases = "!checkSellout", description = "Get sellout games")
    public void checkSellout(){
        storeService.runCheckSellout();
    }

    @Command(aliases = "!getCheckList", description = "Get check list")
    public void checkSellout(ServerTextChannel channel){
     channel.sendMessage(storeService.getCheckList());
    }

}
