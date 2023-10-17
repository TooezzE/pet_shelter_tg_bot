package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

/**
 * Command to get safety tips on shelter's territory
 */
public class SafetyTipsCommand implements Command {

    private final SendBotMessageService service;

    public static final String SAFETY_TIPS_MESSAGE =
            "1. Животных без разрешения кинолога НЕ КОРМИТЬ!\n"
                    + "2. Пальцы в клетку не сувать!\n"
                    + "3. Информация о состоянии здоровья ,темпераменте и рекомммендации по уходу написаны на табличке около клетки\n"
                    + "4. Будьте внимательны и осторожны!";


    public SafetyTipsCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), SAFETY_TIPS_MESSAGE);
    }
}
