package pairmatching.convertor;

import pairmatching.domain.Condition;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class InputConvertor {

    private InputConvertor() {
    }

    private static class InputConvertorHolder {
        private static InputConvertor inputConvertor = new InputConvertor();
    }

    public static InputConvertor getInstance() {
        return InputConvertorHolder.inputConvertor;
    }

    public Condition convertStringToCondition(String input) {
        String[] inputSplit = input.split(",");
        Course course = Course.findCourse(inputSplit[0]);
        Level level = Level.findLevel(inputSplit[1]);
        Mission mission = Mission.findMission(inputSplit[1], inputSplit[2]);
        return new Condition(course, level, mission);
    }
}
