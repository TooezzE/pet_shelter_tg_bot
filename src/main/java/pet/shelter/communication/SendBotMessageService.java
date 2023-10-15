package pet.shelter.communication;

public interface SendBotMessageService {

    void sendMessage(Long chatId, String message);
}
