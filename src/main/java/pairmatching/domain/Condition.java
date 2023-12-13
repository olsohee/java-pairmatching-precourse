package pairmatching.domain;

import pairmatching.domain.mission.Level;
import pairmatching.domain.mission.Mission;

import java.util.Objects;

public class Condition {

    private final Course course;
    private final Level level;
    private final Mission mission;

    public Condition(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return course == condition.course && level == condition.level && mission == condition.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }
}
