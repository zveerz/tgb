package org.makeriga.tgbot.features.ping;

import org.makeriga.tgbot.MakeRigaTgBot;
import org.makeriga.tgbot.Settings;
import org.makeriga.tgbot.features.Feature;

public class PingFeature extends Feature {
    
    public static final String FEATURE_ID = "ping";

    public PingFeature(MakeRigaTgBot bot, Settings settings) {
        super(bot, settings);
    }

    @Override
    public boolean Execute(String text, boolean isPrivateMessage, Integer senderId, String senderTitle, Integer messageId, String chatId) {
        // pong
        if ("ping".equals(text) || (settings.getAdminId().equals(senderId) && "ping".equals(text.toLowerCase()))) {
            sendAntispamMessage(chatId, "pong", !isPrivateMessage ? messageId : null, "ping", senderId);
            return true;
        }

        return false;

    }
    
    @Override
    public String GetId() {
        return FEATURE_ID;
    }

}
