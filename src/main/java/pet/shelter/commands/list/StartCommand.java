package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

/**
 * Command to start bot
 */
public class StartCommand implements Command {

    private final SendBotMessageService service;
    public static final String START_MESSAGE = "Hello";

    public StartCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), START_MESSAGE);
    }
}
