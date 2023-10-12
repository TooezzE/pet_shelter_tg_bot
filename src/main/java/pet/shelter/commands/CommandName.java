package pet.shelter.commands;

public enum CommandName {

    START("/start"),
    HELP("/help"),
    INFO("/info"),
    SCHEDULE("/schedule"),
    CONTACTS("/contacts"),
    SAFETY_TIPS("/safetytips"),
    CONTACT_INFO("/contactinfo"),
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
