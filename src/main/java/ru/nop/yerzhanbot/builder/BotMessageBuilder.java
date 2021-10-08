package ru.nop.yerzhanbot.builder;

import com.vdurmont.emoji.EmojiParser;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.LowLevelComponent;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.util.ArrayList;
import java.util.List;

public class BotMessageBuilder {

    public static final Button GET_MIN_REQ = Button.secondary("getMinReq", EmojiParser.parseToUnicode(":wrench:"));
    private String text;
    private EmbedBuilder embed;
    private boolean minReqButton;

    public BotMessageBuilder withEmbed(EmbedBuilder embed) {
        this.embed = embed;
        return this;
    }

    public BotMessageBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public BotMessageBuilder withMinReqButton() {
        this.minReqButton = true;
        return this;
    }

    public MessageBuilder build() {
        var messageBuilder = new MessageBuilder();

        List<LowLevelComponent> lowLevelComponents = new ArrayList<>();
        if (minReqButton) {
            lowLevelComponents.add(GET_MIN_REQ);
        }
        messageBuilder.addComponents(ActionRow.of(lowLevelComponents));

        if (embed != null) {
            messageBuilder.setEmbed(embed);
        }

        if (text != null && text.isEmpty()) {
            messageBuilder.setContent(text);
        }

        return messageBuilder;
    }

}
