package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

/**
 * Command to put users data into db
 */
public class GuestInfoCommand implements Command {
    private final SendBotMessageService service;
    public static final String GUEST_INFO_MESSAGE = "";
    // необходимо добавить логику записи контактных данных гостя в нашу БД

    public GuestInfoCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), GUEST_INFO_MESSAGE);
    }
}
