package pet.shelter.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pet.shelter.command.ShelterCommand;

import static pet.shelter.command.ShelterCommand.*;

/**
 * Класс  меню для пользователя в виде клавиатуры
 */
@Component
public class KeyBoard {

    private final Logger logger = LoggerFactory.getLogger(KeyBoard.class);

    private TelegramBot telegramBot;

    public KeyBoard(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Метод принимает клавиатуру и текст, и отправляет ответ в чат по "chatId".
     */
    public void sendMenu(Long chatId, ReplyKeyboardMarkup replyKeyboardMarkup, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message).replyMarkup(replyKeyboardMarkup.resizeKeyboard(true));
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during  message: {} ", sendResponse.description());
        }

    }

    /**
     * Метод главного меню приюта.
     */
    public void shelterMenu(Long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{SHELTER_INFO.getCommand(), HOW_ADOPT_ANIMAL_INFO.getCommand()},
                new String[]{SEND_REPORT.getCommand(), VOLUNTEER.getCommand()}
        );
        sendMenu(chatId, replyKeyboardMarkup, "Главное меню приюта. \n" +
                "Для того, чтобы вернуться к выбору приюта, введите команду /start");
    }

    /**
     * Метод отображает меню, с информацией о приюте.
     */
    public void shelterInfoMenu(Long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                GENERAL_SHELTER_INFO.getCommand(), SHELTER_ADDRESS_SCHEDULE.getCommand()
        );
        replyKeyboardMarkup.addRow(new KeyboardButton(VOLUNTEER.getCommand()),
                new KeyboardButton(SEND_CONTACT.getCommand()).requestContact(true));
        replyKeyboardMarkup.addRow(MENU.getCommand());
        sendMenu(chatId, replyKeyboardMarkup, "Вы можете получить информацию о приюте в главном меню");
    }

    /**
     * Метод меню с выбором приюта.
     */
    public void chooseShelter(Long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                CAT.getCommand(), DOG.getCommand()
        );
        sendMenu(chatId, replyKeyboardMarkup, "Здесь вы можете выбрать приют");
    }

    /**
     * Метод меню, с информацией о том, как взять питомца из приюта.
     */
    public void howToTakeAnAnimalFromShelter(Long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                DOCUMENTS_LIST.getCommand()
        );
        replyKeyboardMarkup.addRow(new KeyboardButton(VOLUNTEER.getCommand()),
                new KeyboardButton(SEND_CONTACT.getCommand()).requestContact(true));
        replyKeyboardMarkup.addRow(MENU.getCommand());
        sendMenu(chatId,replyKeyboardMarkup,"Информация о том как взять животное из приюта");
    }


}
