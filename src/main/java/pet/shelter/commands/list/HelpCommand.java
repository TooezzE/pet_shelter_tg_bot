package pet.shelter.commands.list;

import com.pengrad.telegrambot.model.Update;
import pet.shelter.commands.Command;
import pet.shelter.communication.SendBotMessageService;

import static pet.shelter.commands.CommandName.HELP;
import static pet.shelter.commands.CommandName.START;

public class HelpCommand implements Command {

    private final SendBotMessageService service;
    public static final String HELP_MESSAGE = String.format("Avaliable commands:\n\n" +
                    "%s - start\n" +
                    "%s - get commands help",
            START.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService service) {
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        service.sendMessage(update.message().chat().id(), HELP_MESSAGE);
    }
}
