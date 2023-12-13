package pairmatching.domain;

public class Condition {

    private final Course course;
    private final Level level;
    private final Mission mission;

    public Condition(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }
}
