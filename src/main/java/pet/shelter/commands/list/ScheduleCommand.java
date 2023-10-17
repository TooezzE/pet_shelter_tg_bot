package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

/**
 * Command to get address, timetable and driving directions info
 */
public class ScheduleCommand implements Command {

    private final SendBotMessageService service;
    public static final String SCHEDULE_MESSAGE = "Наш приют находится по адресу: [г.Астана ,ул. Побережная д.3]\n"
            + "Мы работаем с [08 : 00 ] до [22 : 00 ]\n"
            + "Схема проезда : [схема]";

    public ScheduleCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), SCHEDULE_MESSAGE);
    }
}
