package pet.shelter.commands;

public enum CommandName {

    //    START("/start"),
//    HELP("/help"),
//    INFO("/info"),
//    SCHEDULE("/schedule"),
//    SECURITY("/security"),
//    SAFETY_TIPS("/safetytips"),
//    GUESS_INFO("/contactinfo"),
//    VOLUNTEER("/volunteer"),
//    UNKNOWN(""),
//    CAT("/cat"),
//    DOG("/dog");
//
    START("/start"),
    HELP("/help"),
    SHELTER_INFO("Информация о приюте"),
    MAIN_MENU("Главное меню"),

    CAT("Кошачий приют"),

    DOG("Собачий приют"),

    SHELTER_INFO_MENU("Узнать информацию о выбранном приюте"),

    HOW_ADOPT_PET_INFO("Как взять животного из приюта"),

    VOLUNTEER("Позвать волонтера"),

    SHELTER_ADDRESS_SCHEDULE("Адрес и график работы приюта"),

    RECOMMENDATIONS_LIST("Список рекомендаций и советов"),

    DOCUMENTS_LIST("Список необходимых документов"),

    SEND_CONTACT("Отправить контактные данные"),

    SEND_REPORT("Прислать отчет о питомце"),

    UNKNOWN("");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
    }
