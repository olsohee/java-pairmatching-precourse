package pairmatching.controller;

import pairmatching.message.ErrorMessage;

import java.util.Arrays;

public enum Command {

    MATCHING("1"),
    CHECK("2"),
    RESET("3"),
    QUIT("Q");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command getCommand(String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.getCommand().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }

    public String getCommand() {
        return command;
    }
}
