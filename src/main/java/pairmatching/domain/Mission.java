package pairmatching.domain;

import pairmatching.message.ErrorMessage;

import java.util.Arrays;

public enum Mission {

    RACING_CAR("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    BASEBALL_GAME("숫자야구게임", Level.LEVEL1),
    SHOPPING("장바구니", Level.LEVEL2),
    PAY("결제", Level.LEVEL2),
    SUBWAY("지하철노선도", Level.LEVEL2),
    PERFORMANCE_IMPROVEMENT("성능개선", Level.LEVEL4),
    LAUNCH("배포", Level.LEVEL4);

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission findMission(String inputLevel, String inputMission) {
        return Arrays.stream(Mission.values())
                .filter(mission -> mission.name.equals(inputMission.trim()))
                .filter(mission -> mission.level.equals(Level.findLevel(inputLevel)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }
}
