package ru.nop.yerzhanbot.listeners.impl;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.listeners.GameCommandsListener;

import java.util.List;

@Slf4j
@Component
public class GameCommandsListenerImpl implements GameCommandsListener {

    public static final String PREFIX = "Ержан";
    private final List<Command> commands;

    public GameCommandsListenerImpl(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        var message = messageCreateEvent.getMessage();
        var content = message.getContent();
        if (StringUtil.isNullOrEmpty(content)
                || message.getAuthor().isBotUser()
                || !content.toLowerCase().contains(PREFIX.toLowerCase())) {
            return;
        }
        log.info(content);

        var commandRequest = removePrefix(content);
        var command = commands.stream()
                .filter(cmd -> isContainsAlias(commandRequest.toLowerCase(), cmd.getAliases()))
                .findFirst()
                .orElse(null);
        if (command == null) {
            messageCreateEvent.getChannel().sendMessage("Да еб, заибал, не понимаю че ты от меня хочешь");
            log.error("Command '{}' not found", commandRequest);
            return;
        }
        command.performCommand(messageCreateEvent.getChannel(),
                replaceAliases(commandRequest, command.getAliases()));
    }

    private boolean isContainsAlias(String message, List<String> aliases) {
        return aliases.stream().anyMatch(alias -> message.toLowerCase().contains(alias.toLowerCase()));
    }

    private String removePrefix(String messageContent) {
        return messageContent.toLowerCase().replaceAll(PREFIX.toLowerCase(), "").trim();
    }

    public String replaceAliases(String text, List<String> aliases) {
        String result = text.toLowerCase();
        for (String alias : aliases) {
            var aliasParts = alias.split("\\s+");
            for (String part : aliasParts)
                result = result.replaceAll(part.toLowerCase(), "");
        }
        return result.trim();
    }

}
