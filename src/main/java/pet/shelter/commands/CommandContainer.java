package pet.shelter.commands;

import pet.shelter.commands.list.*;
import pet.shelter.communication.SendBotMessageService;

import java.util.HashMap;
import java.util.Map;

import static pet.shelter.commands.CommandName.*;

public class CommandContainer {

    private final Map<String, Command> commandMap = new HashMap<>();
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService service) {
        commandMap.put(START.getCommandName(), new StartCommand(service));
        commandMap.put(HELP.getCommandName(), new HelpCommand(service));
        unknownCommand = new UnknownCommand(service);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
