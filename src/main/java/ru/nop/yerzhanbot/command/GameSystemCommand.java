package ru.nop.yerzhanbot.command;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.BotConstants;
import ru.nop.yerzhanbot.service.GameStoreService;

import java.awt.*;

@Component
public class GameSystemCommand implements CommandExecutor {

    private final GameStoreService gameStoreService;

    public GameSystemCommand(GameStoreService gameStoreService) {
        this.gameStoreService = gameStoreService;
    }

    @Command(aliases = BotConstants.BOT_NAME + " find game", description = "")
    public void findGame(ServerTextChannel channel, Message message){
        var game = gameStoreService.findGame(message.getContent());

    }

    @Command(aliases = "!test", description = "Test")
    public void test(ServerTextChannel channel, Message message){
        var embedBuilder = new EmbedBuilder().setColor(Color.RED).setFooter("asdas").setAuthor(message.getAuthor());
        channel.sendMessage(embedBuilder);
    }

}
