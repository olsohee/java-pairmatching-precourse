package pairmatching.message;

public enum OutputMessage {

    LINE("#############################################"),
    COURSE("과정: 백엔드 | 프론트엔드"),
    MISSIONG("미션:\n" +
            "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n" +
            "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n" +
            "  - 레벨3: \n" +
            "  - 레벨4: 성능개선 | 배포\n" +
            "  - 레벨5: ")
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
