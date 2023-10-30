package pet.shelter.command;

import pet.shelter.repository.CatRepository;

/**
 * Класс в котором хранятся сообщения для работы с клавиатурой
 */
public enum ShelterCommand {
    START("/start"),
    CAT("Приют Кошек"),
    DOG("Приют Собак"),
    MENU("Главное меню"),
    SHELTER_INFO("Информация о приюте"),
    HOW_ADOPT_ANIMAL_INFO("Как взять животного из приюта"),
    ADOPT_ANIMAL("Взять животное"),
    VOLUNTEER("Связаться с волонтером"),
    GENERAL_SHELTER_INFO("Общая информация"),
    SHELTER_ADDRESS_SCHEDULE("Адрес и график работы приюта"),
    RECOMMENDATIONS_LIST("Список рекомендаций по питомцу"),
    DOCUMENTS_LIST("Список необходимых документов"),
    SEND_CONTACT("Отправить контактные данные"),
    SEND_REPORT("Отправить отчет о питомце"),
    UNKNOWN_COMMAND("");


    private final String command;

    ShelterCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
