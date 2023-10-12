package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

public class VolunteerCommand implements Command {
    private final SendBotMessageService service;
    public static final String VOLUNTEER_MESSAGE = "Ожидайте! Вызываем волонтёра для помощи";

    public VolunteerCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), VOLUNTEER_MESSAGE);
    }
}
