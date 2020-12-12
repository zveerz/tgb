package org.makeriga.tgbot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);
    
    public static void main(String[] args) {
        
        Settings settings = new Settings();
        settings.setBotToken(System.getProperty("bot_token"));
        settings.setBotUsername(System.getProperty("bot_username"));
        settings.setChatId(System.getProperty("chat_id"));
        settings.setHomeDirectory(System.getProperty("home_dir"));
        try {
            settings.setAdminId(Integer.valueOf(System.getProperty("admin_id")));
        }
        catch (NumberFormatException e) {}
        
        try {
            assert settings.getBotToken() != null && settings.getBotUsername() != null && settings.getChatId() != null && settings.getHomeDirectory() != null;
            
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new MakeRigaTgBot(settings));

            logger.info("Bot started");

        } catch (Throwable t) {
            // log error
            logger.error("Unable to start", t);
        }
    }
}
