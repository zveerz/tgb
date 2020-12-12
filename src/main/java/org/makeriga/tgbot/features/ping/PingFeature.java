package org.makeriga.tgbot.features.ping;

import org.makeriga.tgbot.MakeRigaTgBot;
import org.makeriga.tgbot.Settings;
import org.makeriga.tgbot.features.Feature;

public class PingFeature extends Feature {
    
    public static final String FEATURE_ID = "ping";
    
    public static final String CMD__PING = "ping";

    public PingFeature(MakeRigaTgBot bot, Settings settings) {
        super(bot, settings);
    }

    @Override
    public boolean Execute(String text, boolean isPrivateMessage, Integer senderId, String senderTitle, Integer messageId, String chatId) {
        // pong
        if (CMD__PING.equals(text.toLowerCase())) {
            sendAntispamMessage(chatId, "pong", !isPrivateMessage ? messageId : null, CMD__PING, senderId);
            return true;
        }

        return false;

    }
    
    @Override
    public String GetId() {
        return FEATURE_ID;
    }

}
