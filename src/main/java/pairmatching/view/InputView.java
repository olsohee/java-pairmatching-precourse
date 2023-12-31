package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String READ_COMMAND_MESSAGE = "기능을 선택하세요.";
    private static final String MATCHING = "1. 페어 매칭";
    private static final String CHECK = "2. 페어 조회";
    private static final String RESET = "3. 페어 초기화";
    private static final String QUIT = "Q. 종료";
    private static final String READ_MATCHING_CONDITION = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주";
    private static final String READ_REMATCHING = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n네 | 아니오";

    private InputView() {
    }

    private static class InputViewHolder {
        private static InputView inputView = new InputView();
    }

    public static InputView getInstance() {
        return InputViewHolder.inputView;
    }

    public String readCommand() {
        System.out.println(READ_COMMAND_MESSAGE);
        System.out.println(MATCHING);
        System.out.println(CHECK);
        System.out.println(RESET);
        System.out.println(QUIT);
        return Console.readLine();
    }

    public String readMatchingCondition() {
        System.out.println(READ_MATCHING_CONDITION);
        return Console.readLine();
    }

    public String readRematching() {
        System.out.println(READ_REMATCHING);
        return Console.readLine();
    }
}
