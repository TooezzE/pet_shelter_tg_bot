package pet.shelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pet.shelter.command.ShelterCommand;
import pet.shelter.command.ShelterType;
import pet.shelter.keyboard.KeyBoard;
import pet.shelter.model.CatAdopter;
import pet.shelter.model.DogAdopter;
import pet.shelter.model.Report;
import pet.shelter.model.UserStatus;
import pet.shelter.services.CatAdopterService;
import pet.shelter.services.DogAdopterService;
import pet.shelter.services.ReportService;
import pet.shelter.services.UserStatusService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TelegramBotUpdateListener implements UpdatesListener {

    private static final String REGEX_MESSAGE = "(Питание:)(\\s)(\\W+)(;)\n" +
                                                "(Здоровье:)(\\s)(\\W+)(;)\n" +
                                                "(Поведение:)(\\s)(\\W+)(;)";

    private final Pattern pattern = Pattern.compile(REGEX_MESSAGE);

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    private final TelegramBot telegramBot;
    private final KeyBoard keyBoard;
    private final UserStatusService userStatusService;
    private final CatAdopterService catAdopterService;
    private final DogAdopterService dogAdopterService;
    private final ReportService reportService;
    @Value("${volunteer-chatId}")
    private Long volunteerChatId;

    public TelegramBotUpdateListener(TelegramBot telegramBot, KeyBoard keyBoard,
                                     UserStatusService userStatusService, CatAdopterService catAdopterService,
                                     DogAdopterService dogAdopterService, ReportService reportService) {
        this.telegramBot = telegramBot;
        this.keyBoard = keyBoard;
        this.userStatusService = userStatusService;
        this.catAdopterService = catAdopterService;
        this.dogAdopterService = dogAdopterService;
        this.reportService = reportService;
    }

    @PostConstruct
    public void setUp() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод который принимает текст сообщения пользователя и сравнивает со значениями класса ShelterCommand.
     */
    public static ShelterCommand shelterCommand(String buttonCommand) {
        ShelterCommand[] values = ShelterCommand.values();
        for (ShelterCommand command : values) {
            if (command.getCommand().equals(buttonCommand)) {
                return command;
            }
        }
        return ShelterCommand.UNKNOWN_COMMAND;
    }

    /**
     * Метод который отправляет текстовые сообщения.
     */
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Ошибка при отправке сообщения: {} ", sendResponse.description());
        }
    }

    /**
     * Метод переадресации сообщения волонтеру
     */
    public void sendVolunteerMessage(Long chatId, int messageId) {
        ForwardMessage forwardMessage = new ForwardMessage(volunteerChatId, chatId, messageId);
        SendResponse sendResponse = telegramBot.execute(forwardMessage);
        if (!sendResponse.isOk()) {
            logger.error("Ошибка при отправке сообщения: {} ", sendResponse.description());
        }
    }

    /**
     * Метод для разделения описания под фото для добавления полученного текста в отчет.
     */
    private List<String> splitCaption(String caption) {
        if (caption == null || caption.isBlank()) {
            throw new IllegalArgumentException("Описание фотографии не должно быть пустым! Попробуйте еще раз");
        }
        Matcher matcher = pattern.matcher(caption);
        if (matcher.find()) {
            return new ArrayList<>(List.of(matcher.group(3), matcher.group(7), matcher.group(11)));
        } else {
            throw new IllegalArgumentException("Проверьте правильность введённых данных и отправьте отчёт ещё раз.");
        }
    }

    /**
     * Метод для получения отчета и отправки далее его волонтеру.
     */
    public void getReportAndSendVolunteer(Message message, String nameAnimal) {
        PhotoSize photo = message.photo()[0];
        String caption = message.caption();
        Long chatId = message.chat().id();

        List<String> comparisonCaption = splitCaption(caption);

        String ration = comparisonCaption.get(0);
        String health = comparisonCaption.get(1);
        String behaviour = comparisonCaption.get(2);

        GetFile getFile = new GetFile(photo.fileId());
        GetFileResponse getFileResponse = telegramBot.execute(getFile);

        try {
            File file = getFileResponse.file();
            byte[] fileContent = telegramBot.getFileContent(file);

            long date = message.date();
            Date lastMessage = new Date(date * 1000);
            reportService.loadReport(
                    chatId, nameAnimal, ration, health,
                    behaviour, lastMessage, fileContent);
            sendVolunteerMessage(chatId, message.messageId());
            sendMessage(chatId, "Отчет принят!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка загрузки файла!");
        }
    }

    /**
     * Метод который отслеживает и организовывает процесс общения с пользователем.
     */
    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Start update: {}", update);
                Message message = update.message();
                long chatId = message.chat().id();
                String text = message.text();
                int messageId = message.messageId();
                Contact contact = update.message().contact();

                if (text != null && update.message().photo() == null && contact == null) {
                    switch (shelterCommand(text)) {
                        case START -> {
                            if (userStatusService.getByChatId(chatId).isEmpty()) {
                                sendMessage(chatId, "Привет! Я могу показать информацию о приютах," +
                                                    "как взять животное из приюта и принять отчет о питомце");
                                UserStatus userStatus = new UserStatus();
                                userStatus.setChatId(chatId);
                                userStatusService.saveUserStatus(userStatus);
                            }
                            keyBoard.chooseShelter(chatId);
                        }
                        case CAT -> {
                            if (userStatusService.getByChatId(chatId).isPresent()) {
                                UserStatus userStatus = userStatusService.getByChatId(chatId).get();
                                if (userStatus.getCatAdopter() == null) {
                                    CatAdopter catOwner = new CatAdopter();
                                    catOwner.setChatId(chatId);
                                    catAdopterService.createOwner(catOwner);
                                    userStatus.setCatAdopter(catOwner);
                                }
                                userStatus.setShelterType(ShelterType.CAT);
                                userStatusService.saveUserStatus(userStatus);
                                sendMessage(chatId, "Вы выбрали приют с кошками.");
                                keyBoard.shelterMenu(chatId);
                            }
                        }
                        case DOG -> {
                            if (userStatusService.getByChatId(chatId).isPresent()) {
                                UserStatus context = userStatusService.getByChatId(chatId).get();
                                if (context.getDogAdopter() == null) {
                                    DogAdopter dogOwner = new DogAdopter();
                                    dogOwner.setChatId(chatId);
                                    dogAdopterService.createOwner(dogOwner);
                                    context.setDogAdopter(dogOwner);
                                }
                                context.setShelterType(ShelterType.DOG);
                                userStatusService.saveUserStatus(context);
                                sendMessage(chatId, "Вы выбрали приют с собаками");
                                keyBoard.shelterMenu(chatId);
                            }
                        }
                        case MENU -> {
                            keyBoard.shelterInfoMenu(chatId);
                        }
                        case SHELTER_INFO -> {
                            keyBoard.shelterInfoMenu(chatId);
                        }
                        case HOW_ADOPT_ANIMAL_INFO -> {
                            keyBoard.howToTakeAnAnimalFromShelter(chatId);
                        }
                        case GENERAL_SHELTER_INFO -> {
                            if (userStatusService.getByChatId(chatId).isPresent()) {
                                UserStatus context = userStatusService.getByChatId(chatId).get();
                                if (context.getShelterType().equals(ShelterType.CAT)) {
                                    sendMessage(chatId, """
                                            Информация о кошачем приюте - ...
                                            Рекомендации о технике безопасности  - ...
                                            Контактные данные охраны - ...
                                            """);
                                } else if (context.getShelterType().equals(ShelterType.DOG)) {
                                    sendMessage(chatId, """
                                            Информация о собачем приюте - ...
                                            Рекомендации о технике безопасности  - ...
                                            Контактные данные охраны - ...
                                            """);
                                }
                            }
                        }
                        case SHELTER_ADDRESS_SCHEDULE -> {
                            if (userStatusService.getByChatId(chatId).isPresent()) {
                                UserStatus context = userStatusService.getByChatId(chatId).get();
                                if (context.getShelterType().equals(ShelterType.CAT)) {
                                    sendMessage(chatId, """
                                            Адрес кошачего приюта - ...
                                            График работы - ...
                                            """);
                                } else if (context.getShelterType().equals(ShelterType.DOG)) {
                                    sendMessage(chatId, """
                                            Адрес собачего приюта - ...
                                            График работы - ...
                                            """);
                                }
                            }
                        }
                        case RECOMMENDATIONS_LIST -> {
                            if (userStatusService.getByChatId(chatId).isPresent()) {
                                UserStatus context = userStatusService.getByChatId(chatId).get();
                                if (context.getShelterType().equals(ShelterType.CAT)) {
                                    sendMessage(chatId, """
                                            Правила знакомства с животным - ...
                                            Список рекомендаций - ...
                                            Список причин отказа в выдаче животного - ...
                                            """);
                                } else if (context.getShelterType().equals(ShelterType.DOG)) {
                                    sendMessage(chatId, """
                                            Правила знакомства с животным - ...
                                            Список рекомендаций - ...
                                            Советы кинолога по первичному общению с собакой - ...
                                            Рекомендации по проверенным кинологам для дальнейшего обращения к ним
                                            Список причин отказа в выдаче животного - ...
                                            """);
                                }
                            }
                        }
                        case DOCUMENTS_LIST -> {
                            if (userStatusService.getByChatId(chatId).isPresent()) {
                                UserStatus context = userStatusService.getByChatId(chatId).get();
                                if (context.getShelterType().equals(ShelterType.CAT)) {
                                    sendMessage(chatId, "Для того чтобы взять " +
                                                        "кота из приюта необходимы такие документы: ...");
                                } else if (context.getShelterType().equals(ShelterType.DOG)) {
                                    sendMessage(chatId, "Для того чтобы взять " +
                                                        "собаку из приюта необходимы такие документы: ...");
                                }
                            }
                        }
                        case VOLUNTEER -> {
                            sendMessage(chatId, "Мы передали ваше сообщение волонтеру. " +
                                                "Отправьте контактные данные," +
                                                "с помощью кнопки в меню - 'Отправить контактные данные'");
                            sendVolunteerMessage(chatId, messageId);
                        }


                        case SEND_REPORT -> {
                            sendMessage(chatId, """
                                    Для отчета необходима фотография, рацион,
                                    здоровье и изменение в поведении питомца.
                                    Загрузите фото, а в подписи к нему, скопируйте и заполните текст ниже.
                                                                        
                                    Рацион: ...;
                                    Здоровье: ...;
                                    Поведение: ...;
                                    """);
                        }
                        default -> sendMessage(chatId, "Неизвестная команда!");
                    }
                } else if (update.message().contact() != null && userStatusService
                        .getByChatId(chatId).isPresent()) {
                    UserStatus context = userStatusService.getByChatId(chatId).get();
                    if (context.getShelterType().equals(
                            ShelterType.CAT) && update.message() != null && contact != null) {
                        CatAdopter catOwner = context.getCatAdopter();
                        catOwner.setPhoneNumber(contact.phoneNumber());
                        catOwner.setName(contact.firstName());
                        catAdopterService.updateOwner(catOwner);
                    } else if (context.getShelterType().equals(
                            ShelterType.DOG) && update.message() != null && contact != null) {
                        DogAdopter dogOwner = context.getDogAdopter();
                        dogOwner.setPhoneNumber(contact.phoneNumber());
                        dogOwner.setName(contact.firstName());
                        dogAdopterService.updateOwner(dogOwner);
                    }
                    sendVolunteerMessage(chatId, messageId);
                    sendMessage(chatId, "Ваши контактные данные успешно добавлены");

                } else if (update.message().photo() != null && update.message().caption() != null) {
                    Calendar calendar = new GregorianCalendar();
                    long compareTime = calendar.get(Calendar.DAY_OF_MONTH);
                    long daysOfReports = reportService.getAll().stream()
                            .filter(s -> s.getChatId() == chatId)
                            .count();
                    Date lastMessageDate = reportService.getAll().stream()
                            .filter(s -> s.getChatId() == chatId)
                            .map(Report::getLastMessage)
                            .max(Date::compareTo)
                            .orElse(null);
                    long numberOfDay = 0L;
                    if (lastMessageDate != null) {
                        numberOfDay = lastMessageDate.getDate();
                    } else {
                        numberOfDay = message.date();
                    }
                    if (daysOfReports < 30) {
                        if (compareTime != numberOfDay) {
                            UserStatus context = userStatusService.getByChatId(chatId).get();
                            if (context.getShelterType().equals(ShelterType.CAT)
                                && context.getCatAdopter().getCat() != null) {
                                String petName = context.getCatAdopter().getCat().getName();
                                getReportAndSendVolunteer(message, petName);
                                daysOfReports++;
                            } else if (context.getShelterType().equals(ShelterType.DOG)
                                       && context.getDogAdopter().getDog() != null) {
                                String petName = context.getDogAdopter().getDog().getName();
                                getReportAndSendVolunteer(message, petName);
                                daysOfReports++;
                            } else {
                                sendMessage(chatId, "В вашем профиле нет животного");
                            }
                        } else {
                            sendMessage(chatId, "Вы уже отправляли сегодня отчет");
                        }

                    }
                    if (daysOfReports == 30) {
                        sendMessage(chatId, "Испытательный срок пройден!");
                        sendMessage(volunteerChatId, "Владелец животного с номером " + chatId
                                                             + " прошел испытательный срок!" +
                                                     " Поздравляем вас с новым преданным другом");
                    }

                } else if (update.message().photo() != null && update.message().caption() == null) {
                    sendMessage(chatId, "Отчет нужно присылать с описанием!");
                }

            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Метод контроля отправки отчетов в нужное время
     */
    @Scheduled(cron = "@daily")
    public void sendWarning() {
        for (UserStatus userStatus : userStatusService.getAll()) {
            long chatId = userStatus.getChatId();
            long daysOfReports = reportService.getAll().stream()
                    .filter(s -> Objects.equals(s.getChatId(), chatId))
                    .count();
            if (daysOfReports < 30 && daysOfReports != 0) {
                long twoDay = 172800000;
                Date nowTime = new Date(new Date().getTime() - twoDay);
                Date lastMessageDate = reportService.getAll().stream()
                        .filter(s -> Objects.equals(s.getChatId(), chatId))
                        .map(Report::getLastMessage)
                        .max(Date::compareTo)
                        .orElse(null);
                if (lastMessageDate != null) {
                    if (lastMessageDate.before(nowTime)) {
                        sendMessage(chatId, "Вы не отправляли отчёты уже более двух дней. " +
                                            "Пожалуйста, отправьте отчёт или обратитесь за помощью к волонтёру.");
                        sendMessage(volunteerChatId, "Владелец животного с номером " + chatId
                                                     + " не отправлял отчёты уже более двух дней!");
                    }
                }
            }
        }
    }
}
