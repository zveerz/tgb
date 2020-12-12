package org.makeriga.tgbot.features.whois;

import java.io.File;
import org.makeriga.tgbot.MakeRigaTgBot;
import org.makeriga.tgbot.Settings;
import org.makeriga.tgbot.features.Feature;

public class WhoisFeature extends Feature {
    
    public static final String FEATURE_ID = "whois";
    
    public static final String CMD__WHOIS = "/whois";

    public WhoisFeature(MakeRigaTgBot bot, Settings settings) {
        super(bot, settings);
    }
    
    @Override
    public void Init() {
        super.Init();
        
        // commands descriptions
        AddPublicCommandDescription(CMD__WHOIS, "identifies a member");
    }

    @Override
    public boolean Execute(String text, boolean isPrivateMessage, Integer senderId, String senderTitle, Integer messageId, String chatId) {
        if (text.startsWith(CMD__WHOIS) || CMD__WHOIS.startsWith(getWrappedCommand(CMD__WHOIS))) {
            if (!text.contains(" "))
                return true;
            String query = text.substring(text.indexOf(" ")  + 1).toLowerCase();
            if (query.length() < 1)
                return true;
            do {
                String realName = getBot().getRealName(query);
                if (realName == null)
                    break;
                // send nickname
                sendMessage(chatId, realName, null);
                File f = new File(new File(settings.getHomeDirectory(), "icons"), realName + ".webp");
                if (!f.exists()) 
                    return true;
                sendSticker(chatId, null, f);
                return true;

             }
             while (false);

             sendMessage(chatId, "I don't know.", null);
           return true;
       }
       return false;
    }

    @Override
    public String GetId() {
        return FEATURE_ID;
    }
    
}
