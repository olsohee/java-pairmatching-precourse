package pairmatching.domain.command;

import pairmatching.message.ErrorMessage;

import java.util.Arrays;

public enum RematchingCommand {

    REMATCHING("네"),
    END("아니오")
    ;

    private final String command;

    RematchingCommand(String command) {
        this.command = command;
    }

    public static RematchingCommand getRematchingCommand(String input) {
        return Arrays.stream(RematchingCommand.values())
                .filter(rematchingCommand -> rematchingCommand.getCommand().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }

    public String getCommand() {
        return command;
    }
}
