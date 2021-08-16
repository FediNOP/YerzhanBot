package ru.nop.yerzhanbot.listeners;


import org.javacord.api.event.message.MessageCreateEvent;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.nop.yerzhanbot.command.Command;
import ru.nop.yerzhanbot.command.impl.AddCommand;
import ru.nop.yerzhanbot.listeners.impl.GameCommandsListenerImpl;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class GameCommandsListenerImplTest {

    public static final List<String> ADD_ALIASES = List.of("доабавь", "Добавь", "add", "add in");
    public static final String GAME_ID = "77777";

    @Mock
    private Command addCommand = Mockito.mock(AddCommand.class);

    @Mock
    private MessageCreateEvent messageCreateEvent;

    @InjectMocks
    private GameCommandsListenerImpl gameCommandsListenerImpl;


    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);
        gameCommandsListenerImpl = new GameCommandsListenerImpl(List.of(addCommand));
    }

    @Test
    void shouldPerformCommand() {

//        given(addCommand.getAliases()).willReturn(ADD_ALIASES);
//        given(messageCreateEvent.getMessage()).willReturn( new TextMes
////                GameCommandsListenerImpl.PREFIX + " добавь " + GAME_ID);
//
//        gameCommandsListenerImpl.onMessageCreate(messageCreateEvent);

    }

}