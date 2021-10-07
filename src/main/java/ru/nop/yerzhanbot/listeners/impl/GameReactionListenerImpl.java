package ru.nop.yerzhanbot.listeners.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.entity.message.embed.EmbedField;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.listeners.GameReactionListener;
import ru.nop.yerzhanbot.service.BotFacade;
import ru.nop.yerzhanbot.service.impl.EmbedGameServiceImpl;

@Slf4j
@Component
public class GameReactionListenerImpl implements GameReactionListener {

    private final BotFacade botFacade;

    public GameReactionListenerImpl(BotFacade botFacade) {
        this.botFacade = botFacade;
    }

    @Override
    public void onReactionAdd(ReactionAddEvent reactionAddEvent) {
        var messageId = reactionAddEvent.getMessageId();
        var api = reactionAddEvent.getApi();
        var messageById = api
                .getMessageById(messageId, reactionAddEvent.getChannel());

        var message = messageById.join();
        var embed = message.getEmbeds().stream().findFirst().orElse(null);
        if (embed == null) {
            log.info("Message by id {} not found", messageId);
            return;
        }
        var gameIdFromEmbed = getGameIdFromEmbed(embed);

        if (gameIdFromEmbed != null) {
            var gameMinimumRequirements = botFacade.getGameMinimumRequirements(gameIdFromEmbed);
            var embedBuilder = embed.toBuilder().setFooter(gameMinimumRequirements);
            Message.edit(api, reactionAddEvent.getChannel().getId(), messageId, embedBuilder);
        }
    }

    private String getGameIdFromEmbed(@NonNull Embed embed) {
        return embed.getFields().stream()
                .filter(embedField -> embedField.getName().equals(EmbedGameServiceImpl.ID))
                .findAny()
                .map(EmbedField::getValue)
                .orElse(null);
    }


}
