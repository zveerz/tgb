package org.makeriga.tgbot.features;

import java.io.File;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.makeriga.tgbot.MakeRigaTgBot;
import org.makeriga.tgbot.Settings;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class Feature {
    
    protected Settings settings = null;
    
    public void Init() { }
    
    private final List<Map.Entry<String, String>> publicCommands = new ArrayList<>();
    
    public List<Map.Entry<String, String>> getPublicCommands() {
        return this.publicCommands;
    }
    
    protected void AddPublicCommandDescription(String command, String description) {
        assert (command != null || description != null);
        publicCommands.add(new AbstractMap.SimpleEntry<>(command, description));
    }
    
    public Feature(MakeRigaTgBot bot, Settings settings) {
        this.bot = bot;
        this.settings = settings;
    }
    public abstract String GetId();
    public abstract boolean Execute(String text, boolean isPrivateMessage, Integer senderId, String senderTitle, Integer messageId, String chatId);
    
    private MakeRigaTgBot bot = null;
    
    protected MakeRigaTgBot getBot() {
        return this.bot;
    }
    
    protected void sendAntispamMessage(String chatId, String text, Integer replyTo, String antispamPreffix, Integer senderUserId) {
        this.bot.SendAntispamMessage(chatId, text, replyTo, antispamPreffix, senderUserId);
    }
    
    protected void sendMessage(String chatId, String text, Integer replyTo) {
        this.bot.SendMessage(chatId, text, replyTo);
    }
    
    protected void sendPublicMessage(String text) {
        this.bot.SendPublicMessage(text);
    }
    
    protected void sendSticker(String chatId, Integer replyTo, File stickerFile) {
        this.bot.SendSticker(chatId, replyTo, stickerFile);
    }
    
    protected void sendPhoto(String chatId, Integer replyTo, InputStream is, String fileName) throws TelegramApiException {
        this.bot.SendPhoto(chatId, replyTo, is, fileName);
    }
    
    protected String getWrappedCommand(String command) {
        return command + "@" + settings.getBotUsername();
    }
    
}
