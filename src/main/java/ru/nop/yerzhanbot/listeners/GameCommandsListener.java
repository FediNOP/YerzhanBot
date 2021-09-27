package ru.nop.yerzhanbot.listeners;

import org.javacord.api.listener.message.MessageCreateListener;
import ru.nop.yerzhanbot.command.Command;

import java.util.List;

public interface GameCommandsListener extends MessageCreateListener {
    List<Command> getCommands();
}
