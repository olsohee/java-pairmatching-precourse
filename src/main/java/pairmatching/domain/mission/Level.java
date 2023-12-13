package pairmatching.domain.mission;

import pairmatching.message.ErrorMessage;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static Level findLevel(String input) {
        return Arrays.stream(Level.values())
                .filter(level -> level.name.equals(input.trim()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }
}
