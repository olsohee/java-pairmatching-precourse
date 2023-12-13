package pairmatching.convertor;

import pairmatching.dto.Condition;
import pairmatching.domain.Course;
import pairmatching.domain.mission.Level;
import pairmatching.domain.mission.Mission;
import pairmatching.message.ErrorMessage;

public class InputConvertor {

    private static final String INPUT_DELIMITER = ",";

    private InputConvertor() {
    }

    private static class InputConvertorHolder {
        private static InputConvertor inputConvertor = new InputConvertor();
    }

    public static InputConvertor getInstance() {
        return InputConvertorHolder.inputConvertor;
    }

    public Condition convertStringToCondition(String input) {
        String[] inputSplit = input.split(INPUT_DELIMITER);

        validateExistMission(Level.findLevel(inputSplit[1]));
        validateLength(inputSplit);

        return new Condition(Course.findCourse(inputSplit[0]),
                Level.findLevel(inputSplit[1]),
                Mission.findMission(inputSplit[1], inputSplit[2]));
    }

    private void validateExistMission(Level level) {
        if (!Mission.isExistMissionByLevel(level)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_FOUND_MISSION.getErrorMessage());
        }
    }

    private void validateLength(String[] inputSplit) {
        if (inputSplit.length != 3) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }
}
