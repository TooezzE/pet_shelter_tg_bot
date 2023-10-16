package pet.shelter.communication;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.shelter.commands.CommandContainer;
import pet.shelter.commands.CommandName;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.awt.SystemColor.menu;
import static java.awt.SystemColor.text;
import static java.util.logging.Level.parse;
import static pet.shelter.commands.CommandName.*;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener, SendBotMessageService {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    /**
     * Prefix to identify command from other text
     */
    public static final String COMMAND_PREFIX = "/";
    @Autowired
    private TelegramBot telegramBot;
    private final CommandContainer commandContainer;

    public TelegramBotUpdatesListener() {
        this.commandContainer = new CommandContainer(this);
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Method processes updates coming from user.<br>Recognizes which command-answer to send
     */
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String messText = update.message().text();
            if (messText.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = messText.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(UNKNOWN.getCommandName()).execute(update);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void sendMessage (Long chatId, String message){
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
    }

    /**
     * Method to parse commands. <b>Not ready for usage.</b>
     */
    public static CommandName parse (String commandName){
        CommandName[] values = CommandName.values();
        for (CommandName command : values) {
            if (command.getCommandName().equals(commandName)) {
                return command;
            }
        }
        return UNKNOWN;

    }

}



