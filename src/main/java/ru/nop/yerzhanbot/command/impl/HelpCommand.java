package ru.nop.yerzhanbot.command.impl;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;

import java.util.List;

@Component
public class HelpCommand implements Command {

    public static final String DESCRIPTION = "Получить помощь";
    private final List<Command> commands;

    public HelpCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void performCommand(Server server, TextChannel channel, String message) {
        var embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Команды которые осилит Ержан");
        embedBuilder.setDescription("Ержан [один из вариантов команды] [значение]");

        commands.forEach(command -> {
            var aliases = String.join(", ", command.getAliases());
            embedBuilder.addField(command.getDescription(), aliases);
        });
        channel.sendMessage(embedBuilder);
    }

    @Override
    public List<String> getAliases() {
        return List.of("help", "помоги", "Памаги", "шааа");
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
