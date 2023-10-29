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
import pet.shelter.model.*;
import pet.shelter.repository.CatRepository;
import pet.shelter.repository.DogRepository;
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
    private final CatRepository catRepository;
    private final DogRepository dogRepository;
    private final ReportService reportService;
    @Value("${volunteer-chatId}")
    private Long volunteerChatId;

    public TelegramBotUpdateListener(TelegramBot telegramBot, KeyBoard keyBoard,
                                     UserStatusService userStatusService, CatAdopterService catAdopterService,
                                     DogAdopterService dogAdopterService, CatRepository catRepository, DogRepository dogRepository, ReportService reportService) {
        this.telegramBot = telegramBot;
        this.keyBoard = keyBoard;
        this.userStatusService = userStatusService;
        this.catAdopterService = catAdopterService;
        this.dogAdopterService = dogAdopterService;
        this.catRepository = catRepository;
        this.dogRepository = dogRepository;
        this.reportService = reportService;
    }

    @PostConstruct
    public void setUp() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Method parse user message and compare text with ShelterCommand enum.
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
     * Method sends message to user
     */
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Ошибка при отправке сообщения: {} ", sendResponse.description());
        }
    }

    /**
     * Method to call volunteer
     */
    public void sendVolunteerMessage(Long chatId, int messageId) {
        ForwardMessage forwardMessage = new ForwardMessage(volunteerChatId, chatId, messageId);
        SendResponse sendResponse = telegramBot.execute(forwardMessage);
        if (!sendResponse.isOk()) {
            logger.error("Ошибка при отправке сообщения: {} ", sendResponse.description());
        }
    }

    /**
     * Method to divide photo description to add text into report
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
     * Method to get report and send it to volunteer.
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
     * Method controlling process of communication with user.
     */
    @Override
    public int process(List<Update> updates) {
        List<String> catNames = catRepository.findAll().stream().map(Cat::getName).toList();
        List<String> dogNames = dogRepository.findAll().stream().map(Dog::getName).toList();
        try {
            updates.forEach(update -> {
                logger.info("Start update: {}", update);
                Message message = update.message();
                long chatId = message.chat().id();
                String messageText = message.text();
                int messageId = message.messageId();
                Contact contact = update.message().contact();
                UserStatus userStatus;
                if (!userStatusService.getByChatId(chatId).isPresent()) {
                    userStatus = new UserStatus();
                    userStatus.setChatId(chatId);
                    userStatusService.saveUserStatus(userStatus);
                } else {
                    userStatus = userStatusService.getByChatId(chatId).get();
                }

                if (messageText != null && update.message().photo() == null && contact == null) {
                    switch (shelterCommand(messageText)) {
                        case START -> {
                            sendMessage(chatId, "Привет! Я могу показать информацию о приютах," +
                                    "как взять животное из приюта и принять отчет о питомце");
                            userStatus.setShelterType(null);
                            keyBoard.chooseShelter(chatId);
                        }
                        case CAT -> {
                                userStatus.setShelterType(ShelterType.CAT);
                                userStatusService.saveUserStatus(userStatus);
                                sendMessage(chatId, "Вы выбрали приют с кошками.");
                                keyBoard.shelterMenu(chatId);
                        }
                        case DOG -> {
                                userStatus.setShelterType(ShelterType.DOG);
                                userStatusService.saveUserStatus(userStatus);
                                sendMessage(chatId, "Вы выбрали приют с собаками");
                                keyBoard.shelterMenu(chatId);
                        }
                        case MENU -> {
                            keyBoard.shelterMenu(chatId);
                        }
                        case SHELTER_INFO -> {
                            keyBoard.shelterInfoMenu(chatId);
                        }
                        case ADOPT_ANIMAL -> {
                            boolean alreadyHavePet = false;
                            boolean isCatToAdopt = userStatus.getShelterType() == ShelterType.CAT;
                            if (isCatToAdopt && userStatus.getCatAdopter().getCat() != null) {
                                alreadyHavePet = true;
                                sendMessage(chatId, "Вы уже выбирали кошку ранее, второй обзавестись, к сожалению, нельзя");
                            } else if(!isCatToAdopt && userStatus.getDogAdopter().getDog() != null) {
                                alreadyHavePet = true;
                                sendMessage(chatId, "Вы уже выбирали собаку ранее, второй обзавестись, к сожалению, нельзя");
                            } else if(isCatToAdopt) {
                                CatAdopter catAdopter = new CatAdopter();
                                catAdopter.setChatId(chatId);
                                catAdopterService.createAdopter(catAdopter);
                                userStatus.setCatAdopter(catAdopter);
                                userStatusService.saveUserStatus(userStatus);
                            } else {
                                DogAdopter dogAdopter = new DogAdopter();
                                dogAdopter.setChatId(chatId);
                                dogAdopterService.createAdopter(dogAdopter);
                                userStatus.setDogAdopter(dogAdopter);
                                userStatusService.saveUserStatus(userStatus);
                            }
                            if(isCatToAdopt && catNames.isEmpty()) {
                                sendMessage(chatId, "К сожалению, в приюте сейчас нет кошек, которых можно забрать");
                                keyBoard.shelterMenu(chatId);
                            } else if(!isCatToAdopt && dogNames.isEmpty()) {
                                sendMessage(chatId, "К сожалению, в приюте сейчас нет собак, которых можно забрать");
                                keyBoard.shelterMenu(chatId);
                            } else if(alreadyHavePet) {
                                keyBoard.shelterMenu(chatId);
                            } else {
                                keyBoard.chooseAnimal(chatId, isCatToAdopt);
                            }
                        }
                        case HOW_ADOPT_ANIMAL_INFO -> {
                            keyBoard.howToTakeAnAnimalFromShelter(chatId);
                        }
                        case GENERAL_SHELTER_INFO -> {
                            if (userStatusService.getByChatId(chatId).isPresent()) {
                                if (userStatus.getShelterType().equals(ShelterType.CAT)) {
                                    sendMessage(chatId, """
                                            Информация о кошачем приюте - ...
                                            Рекомендации о технике безопасности  - ...
                                            Контактные данные охраны - ...
                                            """);
                                } else if (userStatus.getShelterType().equals(ShelterType.DOG)) {
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
                                if (userStatus.getShelterType().equals(ShelterType.CAT)) {
                                    sendMessage(chatId, """
                                            Адрес кошачего приюта - ...
                                            График работы - ...
                                            """);
                                } else if (userStatus.getShelterType().equals(ShelterType.DOG)) {
                                    sendMessage(chatId, """
                                            Адрес собачего приюта - ...
                                            График работы - ...
                                            """);
                                }
                            }
                        }
                        case RECOMMENDATIONS_LIST -> {
                            if (userStatusService.getByChatId(chatId).isPresent()) {
                                if (userStatus.getShelterType().equals(ShelterType.CAT)) {
                                    sendMessage(chatId, """
                                            Правила знакомства с животным - ...
                                            Список рекомендаций - ...
                                            Список причин отказа в выдаче животного - ...
                                            """);
                                } else if (userStatus.getShelterType().equals(ShelterType.DOG)) {
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
                                if (userStatus.getShelterType().equals(ShelterType.CAT)) {
                                    sendMessage(chatId, "Для того чтобы взять " +
                                                        "кота из приюта необходимы такие документы: ...");
                                } else if (userStatus.getShelterType().equals(ShelterType.DOG)) {
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
                        default -> {
                            if(!catNames.contains(messageText) && !dogNames.contains(messageText)) {
                                sendMessage(chatId, "Неизвестная команда");
                            }
                        }
                    }
                    if (catNames.contains(messageText)) {
                        Cat cat = catRepository.findCatByName(messageText);
                        cat.setAdopted(true);
                        catRepository.save(cat);
                        userStatus.getCatAdopter().setCat(cat);
                        Report report = new Report();
                        report.setChatId(chatId);
                        reportService.save(report);
                        userStatusService.saveUserStatus(userStatus);
                        catAdopterService.updateAdopter(userStatus.getCatAdopter().getId(), userStatus.getCatAdopter());
                        sendMessage(chatId, "Поздравляю с приобритением питомца. " +
                                "Теперь придется присылать ежедневный отчет");
                        keyBoard.shelterInfoMenu(chatId);
                    } else if (dogNames.contains(messageText)) {
                        Dog dog = dogRepository.findDogByName(messageText);
                        dog.setAdopted(true);
                        dogRepository.save(dog);
                        userStatus.getDogAdopter().setDog(dog);
                        Report report = new Report();
                        report.setChatId(chatId);
                        reportService.save(report);
                        userStatusService.saveUserStatus(userStatus);
                        dogAdopterService.updateAdopter(userStatus.getDogAdopter().getId(), userStatus.getDogAdopter());
                        sendMessage(chatId, "Поздравляю с приобритением питомца. " +
                                "Теперь придется присылать ежедневный отчет");
                        keyBoard.shelterInfoMenu(chatId);
                    }
                } else if (update.message().contact() != null && userStatusService
                        .getByChatId(chatId).isPresent()) {
                    if (userStatus.getShelterType().equals(ShelterType.CAT)
                            && update.message() != null && contact != null) {
                        CatAdopter catAdopter = userStatus.getCatAdopter();
                        catAdopter.setPhoneNumber(contact.phoneNumber());
                        catAdopter.setName(contact.firstName());
                        catAdopterService.updateAdopter(catAdopter.getId(), catAdopter);
                    } else if (userStatus.getShelterType().equals(ShelterType.DOG)
                            && update.message() != null && contact != null) {
                        DogAdopter dogAdopter = userStatus.getDogAdopter();
                        dogAdopter.setPhoneNumber(contact.phoneNumber());
                        dogAdopter.setName(contact.firstName());
                        dogAdopterService.updateAdopter(dogAdopter.getId(), dogAdopter);
                    }
                    sendVolunteerMessage(chatId, messageId);
                    sendMessage(chatId, "Ваши контактные данные успешно добавлены");

                } else if (update.message().photo() != null && update.message().caption() != null) {
                    Calendar calendar = new GregorianCalendar();
                    long compareTime = calendar.get(Calendar.DAY_OF_MONTH);
                    long daysOfReports = reportService.getAll().stream()
                            .filter(r -> r.getChatId() == chatId)
                            .count();
                    Date lastMessageDate = reportService.getAll().stream()
                            .filter(r -> r.getChatId() == chatId)
                            .map(Report::getLastMessage)
                            .max(Date::compareTo)
                            .orElse(null);
                    long numberOfDay;
                    if (lastMessageDate != null) {
                        numberOfDay = lastMessageDate.getDate();
                    } else {
                        numberOfDay = message.date();
                    }
                    if (daysOfReports < 30) {
                        if (compareTime != numberOfDay) {
                            if (userStatus.getShelterType().equals(ShelterType.CAT)
                                && userStatus.getCatAdopter().getCat() != null) {
                                String petName = userStatus.getCatAdopter().getCat().getName();
                                getReportAndSendVolunteer(message, petName);
                                daysOfReports++;
                            } else if (userStatus.getShelterType().equals(ShelterType.DOG)
                                       && userStatus.getDogAdopter().getDog() != null) {
                                String petName = userStatus.getDogAdopter().getDog().getName();
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
     * Method controls reports scheduling in the right time.
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
