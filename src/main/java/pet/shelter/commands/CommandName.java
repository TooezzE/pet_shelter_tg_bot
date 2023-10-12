package pet.shelter.commands;

public enum CommandName {

    START("/start"),
    HELP("/help"),
    INFO("/info"),
    SCHEDULE("/schedule"),
    SECURITY("/security"),
    SAFETY_TIPS("/safetytips"),
    GUESS_INFO("/contactinfo"),
    VOLUNTEER("/volunteer"),
    UNKNOWN("");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
