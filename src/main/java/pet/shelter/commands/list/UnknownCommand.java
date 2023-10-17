package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

/**
 * Send answer if command unrecognized
 */
public class UnknownCommand implements Command {

    private final SendBotMessageService service;
    public static final String UNKNOWN_MESSAGE = "I answer commands starts with slash(/).\n" +
            "Type /help to check commands list";

    public UnknownCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), UNKNOWN_MESSAGE);
    }
}
