package pairmatching.domain;

import pairmatching.message.ErrorMessage;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course findCourse(String input) {
        return Arrays.stream(Course.values())
                .filter(course -> course.name.equals(input.trim()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }
}
