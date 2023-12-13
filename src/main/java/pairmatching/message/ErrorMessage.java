package pairmatching.message;

public enum ErrorMessage {

    INVALID_INPUT("잘못된 입력입니다."),
    NOT_FOUND_MATCHING("매칭 이력이 없습니다."),
    MATCHING_FAIL("매칭에 실패했습니다."),
    NOT_FOUND_MISSION("해당 레벨에는 미션이 없습니다.");

    private static final String ERROR_HEADER = "[ERROR]";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return String.format("%s %s", ERROR_HEADER, errorMessage);
    }
}
