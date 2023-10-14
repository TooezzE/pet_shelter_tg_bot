package pet.shelter.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pet.shelter.commands.CommandName;

public class KeyBoard {
    private final Logger logger = LoggerFactory.getLogger(KeyBoard.class);
    private TelegramBot telegramBot;

    public KeyBoard(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void choiceMenu(Long chatId) {//выбираем приют
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                CommandName.CAT.getCommandName(), CommandName.DOG.getCommandName());
        sendResponseMenu(chatId, replyKeyboardMarkup, "Выберите приют");
    }

    public void shelterMainMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{CommandName.SHELTER_INFO.getCommandName(), CommandName.HOW_ADOPT_PET_INFO.getCommandName()},
                new String[]{CommandName.SEND_CONTACT. getCommandName(), CommandName.VOLUNTEER.getCommandName()});
        sendResponseMenu(chatId, replyKeyboardMarkup, "Ниже представлено главное меню приюта. " +
                "Чтобы вернуться к выбору приюта, напишите команду /start");
    }


    public void sendResponseMenu(long chatId, ReplyKeyboardMarkup replyKeyboardMarkup, String message) {
        SendMessage sendMessage = new SendMessage(
                chatId, message).replyMarkup(replyKeyboardMarkup.resizeKeyboard(true));
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}
