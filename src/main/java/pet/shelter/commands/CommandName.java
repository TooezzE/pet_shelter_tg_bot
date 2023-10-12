package pet.shelter.commands;

public enum CommandName {

    START("/start"),
    HELP("/help"),
    UNKNOWN("");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}