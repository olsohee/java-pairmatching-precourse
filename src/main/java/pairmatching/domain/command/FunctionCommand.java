package pairmatching.domain.command;

import pairmatching.message.ErrorMessage;

import java.util.Arrays;

public enum FunctionCommand {

    MATCHING("1"),
    CHECK("2"),
    RESET("3"),
    QUIT("Q");

    private final String command;

    FunctionCommand(String command) {
        this.command = command;
    }

    public static FunctionCommand getCommand(String input) {
        return Arrays.stream(FunctionCommand.values())
                .filter(functionCommand -> functionCommand.getCommand().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }

    public String getCommand() {
        return command;
    }
}
