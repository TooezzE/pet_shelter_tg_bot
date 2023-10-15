package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

/**
 * Модель класса об информации о приюте
 */
public class InfoCommand implements Command {

    private final SendBotMessageService service;
    public static final String INFO_MASSAGE = "Приют для собак и кошек 'Чистое сердце'\n"
            + "мы рады вас видеть в нашем приюте здесь вы можете выбрать себе питомца, друга ,члена семьи\n"
            + "а главное дать брату меньшему любовь ,заботу и тепло в котором он так нуждается!";

    public InfoCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), INFO_MASSAGE);
    }
}
