package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

/**
 * Command to get info about security
 */
public class SecurityCommand implements Command {

    private final SendBotMessageService service;
    public static final String SECURITY_MESSAGE = "Контактные данные охраны для оформления пропуска на машину: +79500000120";

    public SecurityCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), SECURITY_MESSAGE);
    }
}
