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
        channel.sendMessage(storeService.addGameToCheckLit(message.getContent().replace(ADD, "").trim()));
    }

//    @Command(aliases = "!test", description = "Test")
//    public void test(ServerTextChannel channel, Message message) {
//        var embedBuilder = new EmbedBuilder().setColor(Color.RED).setFooter("asdas").setAuthor(message.getAuthor());
//        channel.sendMessage(embedBuilder);
//    }

}
